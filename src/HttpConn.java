/**
 * Created by dasha on 11/11/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dasha on 11/11/16.
 */
public class HttpConn {

    public String getIdentifier()  {
        String identifier = null;
        String url = "http://localhost:3000/api/getIdentifier";
        URL obj = null;
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            identifier = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return identifier;
    }

    public  String useIdentifier(String id) {
        String url = "http://localhost:3000/api/"+ id;
        URL obj = null;
        HttpURLConnection con = null;
        StringBuffer response = null;
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
