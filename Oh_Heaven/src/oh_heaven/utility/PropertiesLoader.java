package oh_heaven.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesLoader {


    public static final String DEFAUlT_PROP_PATH = "properties/";

    // Read a properties file
    public static Properties loadPropertiesFile(String propertiesFile){

        // Search for the specified properties file
        if (propertiesFile == null) {
            try (InputStream input = new FileInputStream(DEFAUlT_PROP_PATH + "runmode.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                propertiesFile = DEFAUlT_PROP_PATH + prop.getProperty("current_mode");
                System.out.println(propertiesFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Load the specified properties file
        try (InputStream input = new FileInputStream(propertiesFile)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }




}
