package CetaCiemat.Tempo;

import CetaCiemat.ConfigurationManager;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author csuarez
 */
public class TempoClientFactory {
    private static TempoClient instance;
    
    public static TempoClient getInstance() 
            throws FileNotFoundException, IOException {
        if (instance == null) {
            instance = createClientFromConfiguration();
        }
        
        return instance;
    }
    
    private static TempoClient createClientFromConfiguration() 
            throws FileNotFoundException, IOException {
        
        return new TempoClient(
                ConfigurationManager.get("jira.api.url"),
                ConfigurationManager.get("tempo.api.key"));
    }    
}
