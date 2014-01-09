package CetaCiemat.Jira;

import CetaCiemat.ConfigurationManager;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author csuarez
 */
public class JiraClientFactory {
    private static JiraClient instance;
    
    public static JiraClient getInstance() throws FileNotFoundException, IOException {
        if (instance == null) {
            instance = createClientFromConfiguration();
        }
        
        return instance;
    }
    
    private static JiraClient createClientFromConfiguration() throws FileNotFoundException, IOException {
        return new JiraClient(
                ConfigurationManager.get("jira.api.url"),
                ConfigurationManager.get("jira.api.user"),
                ConfigurationManager.get("jira.api.password"),
                ConfigurationManager.get("jira.dotproject.field"));
    }
}
