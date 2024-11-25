package lts;
import org.junit.Test;


public class Test_url_manager {


    ////////// Methods //////////
    @Test
    public void to_array() {

        Url_manager url_manager = new Url_manager("https://google.com");
        Print.test(url_manager.exec() + "\n");

    }


}