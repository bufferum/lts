package lts.webspace;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import org.junit.Rule;
import lts.Reporter_tests;
import lts.files.Property_reader;
import lts.signs.Print;


/** @see lts.webspace.Sql */
public class Test_sql {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    // @Test
    public void sql_select() throws Exception {

        final String RESOURCES = "src/test/resources/Property_reader/";
        File file_property = new File(RESOURCES + "application.properties");
        Property_reader reader = new Property_reader(file_property);

        String db_ip = reader._get_value("db_ip");
        String db_port = reader._get_value("db_port");
        String db_user = reader._get_value("db_username");
        String db_password = reader._get_value("db_password");
        String db_name = reader._get_value("db_name");

        Sql sql = new Sql(db_ip, db_port, db_user, db_password, db_name);
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

        String db_ip = reader._get_value("db_ip");
        String db_port = reader._get_value("db_port");
        String db_user = reader._get_value("db_username");
        String db_password = reader._get_value("db_password");
        String db_name = reader._get_value("db_name");

        Sql sql = new Sql(db_ip, db_port, db_user, db_password, db_name);
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

        String db_ip = reader._get_value("db_ip");
        String db_port = reader._get_value("db_port");
        String db_user = reader._get_value("db_username");
        String db_password = reader._get_value("db_password");
        String db_name = reader._get_value("db_name");

        String bot_thread_max = reader._get_value("bot_thread_max");
        String bot_thread_min = reader._get_value("bot_thread_min");
        String bot_thread_timeout = reader._get_value("bot_thread_timeout");

        Sql sql = new Sql(db_ip, db_port, db_user, db_password, db_name, bot_thread_max, bot_thread_min, bot_thread_timeout);
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

        String db_ip = reader._get_value("db_ip");
        String db_port = reader._get_value("db_port");
        String db_user = reader._get_value("db_username");
        String db_password = reader._get_value("db_password");
        String db_name = reader._get_value("db_name");

        String bot_thread_max = reader._get_value("bot_thread_max");
        String bot_thread_min = reader._get_value("bot_thread_min");
        String bot_thread_timeout = reader._get_value("bot_thread_timeout");

        Sql sql = new Sql(db_ip, db_port, db_user, db_password, db_name, bot_thread_max, bot_thread_min, bot_thread_timeout);
        Connection connect = sql._get_connect();

        ResultSet result = Sql._select(connect, "SELECT type FROM `Contacts`;");
                  result.next();

        Print.result("Type: " + result.getString("type"));

        connect.close();
        sql._close_connect_pool();

    }


}