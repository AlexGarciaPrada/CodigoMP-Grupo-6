package combate2000lasecuela;
import java.io.*;

public class Loader implements Serializable {
    private String filename;
    private String[] parts;
    private String minion;

    public Loader(String filename) {
        this.filename = filename;
    }

    public String read() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return minion;
    }


}
