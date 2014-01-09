/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CetaCiemat.Jira;

import java.io.IOException;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

/**
 *
 * @author csuarez
 */
public class JiraClient {
    private String endpoint;
    private DotprojectUrlCache cache;
    private Resty httpClient;
    private String dotProjectField;
    
    public JiraClient (String endpoint, String user, String password, String dotProjectField) {
        this.endpoint = endpoint;
        this.dotProjectField = dotProjectField;
        this.cache = new DotprojectUrlCache();
        String credentials = ApiUtils.getCodedCredentials(user, password);
        this.httpClient = new Resty();
        this.httpClient.alwaysSend("Authorization", "Basic " + credentials);
    }
    
    public Integer getDotProjectTaskId(String idTask) throws IOException, DotprojectFieldException {
        if (cache.containsKey(idTask)) {
            return cache.get(idTask);
        }
        
        JSONResource response = this.httpClient.json(
                this.endpoint
                + "/rest/api/2/issue/"
                + idTask);
        
        String dotprojectUrl;
        
        try {
             dotprojectUrl = response.object().getJSONObject("fields").getString(this.dotProjectField);
        } catch (Exception ex) {
            throw new DotprojectFieldException(
                    "There was a problem reading the custom field!",
                    ex);
        }
        
        Integer dotprojectTaskId = 
                ApiUtils.extractTaskIdFromDotprojectUrl(dotprojectUrl);
        
        cache.put(idTask, dotprojectTaskId);
        return dotprojectTaskId;  
    }
}
