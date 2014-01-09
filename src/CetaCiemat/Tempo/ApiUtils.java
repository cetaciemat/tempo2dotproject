package CetaCiemat.Tempo;

import CetaCiemat.Jira.JiraClientFactory;
import CetaCiemat.Jira.DotprojectFieldException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import us.monoid.web.XMLResource;

/**
 *
 * @author csuarez
 */
public class ApiUtils {
    public static Integer getWorkLogCount(XMLResource xml) throws Exception {
        String countAsString = xml.get("worklogs/@number_of_worklogs", String.class);
        return Integer.valueOf(countAsString);
    }
    
    public static ArrayList<WorkLog> extractWorkLogs(XMLResource xml, List<String> keys) throws Exception { 
        ArrayList<WorkLog> logs = new ArrayList<WorkLog>();
        int count = ApiUtils.getWorkLogCount(xml);
        for (int i = 1; i <= count; i++) {
            WorkLog log = new WorkLog();
            
            String issueKey =  xml.get("worklogs/worklog[" + i + "]/issue_key", String.class);
            
            if ((keys == null) || (keys.contains(issueKey))) {           
                log.setIssueKey(issueKey);
                try {
                    log.setHours(
                        Float.valueOf(xml.get("worklogs/worklog[" + i + "]/hours", String.class)));
                } catch (NumberFormatException ex) {
                    log.setHours(0f);
                }

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                log.setWorkDate(
                        df.parse(xml.get("worklogs/worklog[" + i + "]/work_date", String.class)));

                log.setUsername(
                        xml.get("worklogs/worklog[" + i + "]/username", String.class));

                log.setWorkDescription(
                        xml.get("worklogs/worklog[" + i + "]/work_description", String.class));

                try {
                    Integer dotprojectTaskId = JiraClientFactory.getInstance().getDotProjectTaskId(log.getIssueKey());
                    log.setDotProjectTaskId(dotprojectTaskId);
                } catch (DotprojectFieldException ex) {   
                    Logger.getLogger(ApiUtils.class.getName()).log(
                            Level.WARNING,
                            "Log in {0} won't be imported. Reason: " + ex.getMessage(),
                            new Object[]{log.getIssueKey()});
                }

                logs.add(log);
            }
        }
        return logs;
    }
}
