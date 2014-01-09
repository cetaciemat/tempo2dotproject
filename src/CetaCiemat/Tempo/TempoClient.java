package CetaCiemat.Tempo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import us.monoid.web.Resty;
import us.monoid.web.XMLResource;

/**
 *
 * @author csuarez
 */
public class TempoClient {
    
    private String endpoint;
    private String apiKey;
    
    public TempoClient(String endpoint, String apiKey) {
       this.endpoint = endpoint;
       this.apiKey = apiKey;
    }
    
    public ArrayList<WorkLog> getWorklogsOfUsers(String startDate, String endDate, List<String> users, List<String> keys) throws Exception {
        
        ArrayList<WorkLog> worklogs = new ArrayList<WorkLog>();
        
        String baseUrl = this.endpoint
                + "/plugins/servlet/tempo-getWorklog/"
                + "?dateFrom=" + startDate
                + "&dateTo=" + endDate
                + "&tempoApiToken=" + this.apiKey
                + "&format=xml"
                + "&addIssueDescription=true"
                + "&userName=";
        
        for (String user : users) {
            XMLResource result = new Resty().xml(baseUrl + user);
            
            ArrayList<WorkLog> userLogs = ApiUtils.extractWorkLogs(result, keys);       
               
            Logger.getLogger(TempoClient.class.getName()).log(
                Level.INFO,
                "{0} logs will be imported for user {1}",
                new Object[]{userLogs.size(), user});
        
            worklogs.addAll(userLogs);
        }
        
        return worklogs;
    }
}
