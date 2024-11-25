package lts;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import org.junit.Test;


/** @see lts.Sql */
public class Test_sql {


    ////////// Methods //////////
    @Test
    public void sql_test_main() {

        Print.test("\n[SQL]\n");

    }

    // @Test
    public void sql_select() throws Exception {

        final String RESOURCES = "src/test/resources/Property_reader/";
        File file_property = new File(RESOURCES + "application.properties");
        Property_reader reader = new Property_reader(file_property);

        String ip = reader._get_value("bd_ip");
        String port = reader._get_value("bd_port");
        String user = reader._get_value("bd_user");
        String password = reader._get_value("bd_password");
        String db_name = reader._get_value("bd_name");

        Sql sql = new Sql(ip, port, user, password, db_name);
        Connection connect = sql._get_connect();

        ResultSet result = Sql._select(connect, "SELECT type FROM `Contacts`;");
                  result.next();

        Print.result("Type: " + result.getString("type"));

        sql._close_connect_pool();
        connect.close();

    }

    // @Test
    public void sql_insert() throws Exception {

        final String RESOURCES = "src/test/resources/Property_reader/";
        File file_property = new File(RESOURCES + "application.properties");
        Property_reader reader = new Property_reader(file_property);

        String ip = reader._get_value("bd_ip");
        String port = reader._get_value("bd_port");
        String user = reader._get_value("bd_user");
        String password = reader._get_value("bd_password");
        String db_name = reader._get_value("bd_name");

        Sql sql = new Sql(ip, port, user, password, db_name);
        Connection connect = sql._get_connect();

        Sql._update(connect, "INSERT INTO `Contacts`(name) VALUES(?);", "user_test_2");

        sql._close_connect_pool();
        connect.close();

    }

    // @Test
    public void sql_batch() throws Exception {

        final String RESOURCES = "src/test/resources/Property_reader/";
        File file_property = new File(RESOURCES + "application.properties");
        Property_reader reader = new Property_reader(file_property);

        String ip = reader._get_value("bd_ip");
        String port = reader._get_value("bd_port");
        String user = reader._get_value("bd_user");
        String password = reader._get_value("bd_password");
        String db_name = reader._get_value("bd_name");

        String bot_thread_max = reader._get_value("bot_thread_max");
        String bot_thread_min = reader._get_value("bot_thread_min");
        String bot_thread_timeout = reader._get_value("bot_thread_timeout");

        Sql sql = new Sql(ip, port, user, password, db_name, bot_thread_max, bot_thread_min, bot_thread_timeout);
        Connection connect = sql._get_connect();

        Sql._batch()._set_query(connect, "INSERT INTO `Contacts`(num_phone, name, type) VALUES(?, ?, ?);")
                    ._set_param(null, "Name", "INFO")
                    ._set_param("Num", "Name_2", "INFO")
                    ._execute_batch();

        sql._close_connect_pool();
        connect.close();

    }

    // @Test
    public void sql_select_pool() throws Exception {

        final String RESOURCES = "src/test/resources/Property_reader/";
        File file_property = new File(RESOURCES + "application.properties");
        Property_reader reader = new Property_reader(file_property);

        String ip = reader._get_value("bd_ip");
        String port = reader._get_value("bd_port");
        String user = reader._get_value("bd_user");
        String password = reader._get_value("bd_password");
        String db_name = reader._get_value("bd_name");

        String bot_thread_max = reader._get_value("bot_thread_max");
        String bot_thread_min = reader._get_value("bot_thread_min");
        String bot_thread_timeout = reader._get_value("bot_thread_timeout");

        Sql sql = new Sql(ip, port, user, password, db_name, bot_thread_max, bot_thread_min, bot_thread_timeout);
        Connection connect = sql._get_connect();

        ResultSet result = Sql._select(connect, "SELECT type FROM `Contacts`;");
                  result.next();

        Print.result("Type: " + result.getString("type"));

        connect.close();
        sql._close_connect_pool();

    }


}