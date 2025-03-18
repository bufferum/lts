package lts.webspace;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import com.zaxxer.hikari.HikariDataSource;
import lts.signs.Print;


/**
 * <h4>Managment for SQL.</h4>
 *
 * @patterns Singleton, Momento
 * @version 2.0
 * @author bufferum
 */
@DefaultQualifier(NonNull.class)
public class Sql {


    ////////// Variables //////////
    private String db_ip;
    private String db_port;
    private String db_username;
    private String db_password;
    private String db_name;
    private String db_max_thread;
    private String db_min_thread;
    private String db_timeout;

    private Boolean is_pool_connect;
    private HikariDataSource data_source;


    ////////// Constructors //////////
    /** The usual way to connect to a database for a single thread */
    public Sql(String db_ip, String db_port, String db_username, String db_password, String db_name) {

        this.db_ip = db_ip;
        this.db_port = db_port;
        this.db_username = db_username;
        this.db_password = db_password;
        this.db_name = db_name;

        this.is_pool_connect = false;

    }

    /**
     * This class provides the ability to have multiple database connections
     * if the program requires multithreading.
     */
    public Sql(String db_ip, String db_port, String db_username, String db_password, String db_name, String db_max_thread, String db_min_thread, String db_timeout) {

        this.db_ip = db_ip;
        this.db_port = db_port;
        this.db_username = db_username;
        this.db_password = db_password;
        this.db_name = db_name;

        this.db_max_thread = db_max_thread;
        this.db_min_thread = db_min_thread;
        this.db_timeout = db_timeout;

        this.is_pool_connect = true;

    }


    ////////// Methods //////////
    public Connection _get_connect() throws SQLException {

        try {

            if(this.is_pool_connect) {

                data_source = new HikariDataSource();
                    data_source.setJdbcUrl("jdbc:mysql://" + this.db_ip + ":" + this.db_port + "/" + this.db_name);
                    data_source.setUsername(this.db_username);
                    data_source.setPassword(this.db_password);
                    data_source.setMaximumPoolSize(Integer.parseInt(this.db_max_thread));
                    data_source.setMinimumIdle(Integer.parseInt(this.db_min_thread));
                    data_source.setIdleTimeout(Long.valueOf(this.db_timeout));

                return data_source.getConnection();
            }
            else {

                return DriverManager.getConnection(
                    "jdbc:mysql://" + this.db_ip + ":" + this.db_port + "/" + this.db_name,
                    this.db_username,
                    this.db_password
                );
            }

        }
        catch(SQLException e) {

            Print.printer_disable();
            throw new SQLException(Print.error("[SQL_SET_CONNECT] - " + e.getMessage()));
        }

    }

    public void _close_connect_pool() { if(this.is_pool_connect) { data_source.close(); } }

    private static PreparedStatement create_prep_state(Connection connection, String query) throws SQLException {

        return connection.prepareStatement(
            query,
            ResultSet.TYPE_SCROLL_SENSITIVE,
            ResultSet.CONCUR_UPDATABLE
        );
    }

    private static PreparedStatement create_prep_state(Connection connection, String query, Object... parameters) throws SQLException {

        PreparedStatement statement = create_prep_state(connection, query);

        for(int i = 0; i < parameters.length; i++) {

            statement.setObject(i + 1, parameters[i]);

        }

        return statement;
    }


    /** Data in the form of a table */
    public static ResultSet _select(Connection connection, String query) throws SQLException {

        return create_prep_state(connection, query).executeQuery();
    }

    /** Data in the form of a table */
    public static ResultSet _select(Connection connection, String query, Object... parameters) throws SQLException {

        return create_prep_state(connection, query, parameters).executeQuery();
    }


    /** Execute SQL query without return response */
    public static void _update(Connection connection, String query) throws SQLException {

        create_prep_state(connection, query).executeUpdate();

    }

    /** Execute SQL query without return response */
    public static void _update(Connection connection, String query, Object... parameters) throws SQLException {

        create_prep_state(connection, query, parameters).executeUpdate();

    }


    /**
     * <p>It is used when the same sql query needs to be run many times, but with different data.
     *
     * <p>The algorithm of actions:
     * <p>1. The sql query itself is inserted into the prepared_statement
     *       using the _set_query method
     * <p>2. Next, using the Execute Batch Query(Builder), the parameters
     *       are set through the dot as many times as required
     * <p>3. Then the last method is called, which is executed by sql query
     *
     * <p>Example:
     * <pre>
            Sql._batch()._set_query("INSERT INTO `Book`(name, type) VALUES(?, ?);")
                ._set_param("user_test_2", "type_2")
                ._set_param("user_test_3", "type_3")
                ._execute_batch();
     * </pre>
     *
     * <h4>Attention, the number of characters (<code>?</code>) must match the length of the array that is set in the _set_parameter method!!</h4>
    */
    public static Executer_batch_query _batch() throws SQLException { return new Executer_batch_query(); }


    ////////// Class //////////
    public static class Executer_batch_query {


        ////////// Constructors //////////
        public static PreparedStatement prepared_statement;


        ////////// Constructors //////////
        private Executer_batch_query() { prepared_statement = null; }


        ////////// Methods //////////
        public Allow_executer_batch_query _set_query(Connection connection, String query) throws SQLException {

            prepared_statement = create_prep_state(connection, query);

            return new Allow_executer_batch_query();
        }


        ////////// Class //////////
        public static class Allow_executer_batch_query {


            ////////// Methods //////////
            public Allow_executer_batch_query_2 _set_param(Object... parameters) throws SQLException {

                for(int i = 0; i < parameters.length; i++) {

                    prepared_statement.setObject(i + 1, parameters[i]);

                }

                prepared_statement.addBatch();

                return new Allow_executer_batch_query_2();
            }


            ////////// Class //////////
            public static class Allow_executer_batch_query_2 {


                ////////// Methods //////////
                public Allow_executer_batch_query_2 _set_param(Object... parameters) throws SQLException {

                    for(int i = 0; i < parameters.length; i++) {

                        prepared_statement.setObject(i + 1, parameters[i]);

                    }

                    prepared_statement.addBatch();

                    return this;
                }

                public int[] _execute_batch() throws SQLException {

                    return prepared_statement.executeBatch();
                }


            }

        }


    }


}