package za.co.carols_boutique.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class CarolsProperties {

    private  File file = new File("CarolsDatabase.properties");
    private  InputStream is;
    private  String url;
    private  String username;
    private  String password;
    private  Properties p = new Properties();

    public CarolsProperties() {
        try {
            p = new Properties();
            is = new FileInputStream(file);

            p.load(is);
        } catch (IOException ignore) {
        }
        setUrl(p.getProperty("url"));
        setUsername(p.getProperty("username"));
        setPassword(p.getProperty("password"));
    }
    
    public  String read(String key){       
        return p.getProperty(key);
    }
    
    public  String getUrl() {
        return url;
    }

    public  String getUsername() {
        return username;
    }

    public  String getPassword() {
        return password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
