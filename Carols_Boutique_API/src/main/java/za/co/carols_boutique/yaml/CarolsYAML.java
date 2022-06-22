/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.yaml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;



/**
 *
 * @author muaad
 */
public class CarolsYAML {
    private String url;
    private String username;
    private String password;
//    private InputStream is;
    private BufferedReader br;
    private Yaml yaml;
    private Map<String, String> data;

    public CarolsYAML() {
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\muaad\\OneDrive\\Desktop\\Carols_Boutique_BE\\CarolsDatabase.yml"));
            yaml = new Yaml();
            data = yaml.load(br);
            setUrl(data.get("url"));
            setPassword(data.get("password"));
            setUsername(data.get("username"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        try {
//            is = new FileInputStream(new File("C:\\Users\\muaad\\OneDrive\\Desktop\\Carols_Boutique_BE\\CarolsDatabase.yml"));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(CarolsYAML.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        yaml = new Yaml();
//        data = yaml.load(is);
//        setUrl(data.get("url"));
//        setUsername(data.get("username"));
//        setPassword(data.get("password"));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CarolsYAML{" + "url=" + url + ", username=" + username + ", password=" + password + '}';
    }
    
    
}
