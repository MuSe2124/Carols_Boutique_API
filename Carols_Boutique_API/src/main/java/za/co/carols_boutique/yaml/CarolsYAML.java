package za.co.carols_boutique.yaml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author muaad
 */
public class CarolsYAML {
    private BufferedReader br;
    private Yaml yaml;
    private HashMap data;
    private String url;
    private String password;
    private String username;

    public CarolsYAML() {
        try {
            br = new BufferedReader(new FileReader("CarolsDatabase.yml"));
            yaml = new Yaml();
            data = yaml.load(br);
            setUrl(data.get("url").toString());
            setPassword(data.get("password").toString());
            setUsername(data.get("username").toString());
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
