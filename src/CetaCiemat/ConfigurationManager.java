package CetaCiemat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * It manages the app configuration.
 * 
 * @author csuarez
 */
public class ConfigurationManager {
    private static String CONFIG_PATH = "config.properties";
    private static Properties properties = new Properties();
   
    /**
     * Returns a parameter from the configuration file.
     * @param parameter Parameter to load.
     * @return The value of the parameter.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static String get(String parameter) 
            throws FileNotFoundException, IOException {
        properties.load(new FileInputStream(CONFIG_PATH));
        return properties.getProperty(parameter);
    }  
}
