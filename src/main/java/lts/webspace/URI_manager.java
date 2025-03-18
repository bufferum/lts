package lts.webspace;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * <h4>Will execute URL requests.</h4>
 *
 * @version 2.0
 * @author bufferum
 */
public class URI_manager {


    ////////// Variables //////////
    private HttpResponse<String> response;


    ////////// Constructors //////////
    public URI_manager() { }
    public URI_manager(String uri_query) throws IOException,
                                          URISyntaxException,
                                          InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        URI uri = new URI(uri_query);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

    }


    ////////// Methods //////////
    public Integer _get_response_code() {

        return response.statusCode();
    }

    public String _get_result() {

        return response.body();
    }


    public static String get_my_ip_external() throws URISyntaxException,
                                                     IOException,
                                                     InterruptedException {

        return new URI_manager("https://icanhazip.com")._get_result();
    }

    public static String get_domain_host() throws UnknownHostException {

        return InetAddress.getLocalHost().getCanonicalHostName();
    }

    public static String get_host_ip() throws UnknownHostException {

        return InetAddress.getLocalHost().getHostAddress();
    }

    public static String get_hostname() throws UnknownHostException {

        return InetAddress.getLocalHost().getHostName();
    }


}