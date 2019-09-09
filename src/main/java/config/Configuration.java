package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class Configuration {

    private static Properties properties;
    private static Configuration configuration;

    private Configuration() {
        this.properties = new Properties();
        try {
            properties.load(new FileReader("/home/aizeeck/Dropbox/vproperties.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getInstance(){
        // Uses singleton pattern to guarantee the creation of only one instance
        if(configuration == null) {
            if (configuration == null) {
                configuration = new Configuration();
            }
        }
        return configuration;
    }

    public String getProperty(String key){
        String result = null;
        if(key !=null && !key.trim().isEmpty()){
            result = this.properties.getProperty(key);
        }
        return result;
    }
}
