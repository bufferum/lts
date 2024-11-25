package lts;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/** Will execute URL requests */
public class Url_manager {


    ////////// Variables //////////
    private HttpURLConnection con;


    ////////// Constructors //////////
    @SuppressWarnings("deprecation")
    public Url_manager(String url) {

        try {

            URL url_query = new URL(url);

            con = (HttpURLConnection) url_query.openConnection();

        }
        catch(MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

    }


    ////////// Methods //////////
    public Integer exec() {

        try {

            return con.getResponseCode();
        }
        catch(IOException e) { e.printStackTrace(); }

        return 0;
    }


}