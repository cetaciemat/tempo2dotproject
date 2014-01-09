package CetaCiemat.DotProject;

import CetaCiemat.ConfigurationManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author csuarez
 */
public class DotProjectClientFactory {
    private static DotProjectClient instance;
    
    public static DotProjectClient getInstance() 
            throws SQLException, FileNotFoundException, IOException {
        if (instance == null) {
            instance = createClientFromConfiguration();
        }
        
        return instance;
    }
    
    private static DotProjectClient createClientFromConfiguration() 
            throws SQLException, FileNotFoundException, IOException {
        return new DotProjectClient(
                ConfigurationManager.get("dotproject.db.url"),
                ConfigurationManager.get("dotproject.db.user"),
                ConfigurationManager.get("dotproject.db.password"),
                ConfigurationManager.get("dotproject.db.encoding"));
    }    
}
