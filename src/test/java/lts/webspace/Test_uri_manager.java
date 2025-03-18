package lts.webspace;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Rule;
import org.junit.Test;
import lts.Reporter_tests;
import lts.files.File_manager;
import lts.signs.Print;


/** @see lts.webspace.URI_manager */
public class Test_uri_manager {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();
    private static final String RESOURCES = "src/test/resources/Test_uri_manager/";


    ////////// Methods //////////
    @Test public void get_result() {

        try {

            URI_manager uri_manager = new URI_manager("https://api.gismeteo.net/v2/weather/current/");

            String result = uri_manager._get_result();
            Print.result("\nCode: " + uri_manager._get_response_code());
            Print.result("\nResult: " + result);

            Document document = Jsoup.parse(result);;
            result = document.outerHtml();

            try {
                // The file will be created automatically if it doesn't exist yet.
                File_manager._set_file(RESOURCES + "index.html", true)
                            ._edit(result);

            }
            catch(Exception e) { e.printStackTrace(); }

        }
        catch(URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test public void get_host_ip() {

        try { Print.result("\nIP = " + URI_manager.get_host_ip()); }
        catch(UnknownHostException e) { e.printStackTrace(); }

    }

    @Test public void get_hostname() {

        try { Print.result("\nHostname = " + URI_manager.get_hostname()); }
        catch(UnknownHostException e) { e.printStackTrace(); }

    }

    @Test public void get_domain_host() {

        try { Print.result("\nDomain host = " + URI_manager.get_domain_host()); }
        catch(UnknownHostException e) { e.printStackTrace(); }

    }

    @Test public void get_my_ip_external() {

        try {

            Print.result("\nYour external ip: " + URI_manager.get_my_ip_external());

        }
        catch(URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }


}