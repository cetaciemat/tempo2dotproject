package CetaCiemat;

import CetaCiemat.DotProject.DotProjectClientFactory;
import CetaCiemat.Tempo.TempoClientFactory;
import CetaCiemat.Tempo.WorkLog;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csuarez
 */
public class Main {
    
    
    private static HashMap <String, Object> getParameters(String[] args) throws InvalidParameterException {
        HashMap <String, Object> arguments = new HashMap <String, Object>();
        if ((args.length < 3) || (args.length > 5)) {
            throw new InvalidParameterException("Invalid number of parameters!");
        }
        
        arguments.put("initDate", args[0]);
        arguments.put("endDate", args[1]);
        arguments.put("users", Arrays.asList(args[2].split(",")));
        arguments.put("keys", null);
        boolean testMode = false;
        
        if (args.length == 4) {
            if ((args[3].equals("--test-mode"))) {
                testMode = true;
            } else {
                arguments.put("keys", Arrays.asList(args[3].split(",")));
            }
        } else if (args.length == 5) {
            if ((args[3].equals("--test-mode"))) {
                testMode = true;
                arguments.put("keys", Arrays.asList(args[4].split(",")));
            } else if ((args[4].equals("--test-mode"))) {
                testMode = true;
                arguments.put("keys", Arrays.asList(args[3].split(",")));
            } else {
                throw new InvalidParameterException("Invalid parameters!");
            }
        }

        arguments.put("testMode", testMode);

        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        format.setLenient(false);
        try { 
            format.parse((String) arguments.get("initDate"));
            format.parse((String) arguments.get("endDate"));
        } catch (ParseException ex) {
            throw new InvalidParameterException("Invalid date format!");
        }
        
        return arguments;
    }
    
    private static void printHelp () {
        System.out.println("Usage:");
        System.out.println("java -jar tempo2dotproject.jar start-date end-date users <keys> <--test-mode>");
        System.out.println("* start-date: MANDATORY. Minimun date of the logs. Expected format: yyyy-mm-dd");
        System.out.println("* end-date: MANDATORY. Maximum date of the logs. Expected format: yyyy-mm-dd");
        System.out.println("* users: MANDATORY. The users of the logs. Expected format: username1,username2,username3");
        System.out.println("* keys: OPTIONAL. Limits the import to the specified keys. Expected format: PROJECT-1,PROJECT-2,PROJECT-3");
        System.out.println("* --test-mode: OPTIONAL. It disables the writings to DotProject (useful for test purposes).");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        try {      
            HashMap<String, Object> arguments = getParameters(args);
     
            
            boolean testMode = false;
            
            if ((Boolean) arguments.get("testMode")) {
                Logger.getLogger(Main.class.getName()).log(Level.INFO, "** TEST MODE **");
                testMode = true;
            }
            
            ArrayList<WorkLog> logs = TempoClientFactory.getInstance()
                    .getWorklogsOfUsers(
                    (String) arguments.get("initDate"),
                    (String) arguments.get("endDate"),
                    (List<String>) arguments.get("users"),
                    (List<String>) arguments.get("keys"));
            
            DotProjectClientFactory.getInstance().addLogs(logs, testMode);
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Finished import :)");
        } catch (InvalidParameterException ex) {
            System.out.println(ex.getMessage());
            System.out.println("");
            printHelp();
        
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Aborted import D:", ex);
        }    
     }
}
