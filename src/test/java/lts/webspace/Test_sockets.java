package lts.webspace;
import org.junit.Rule;
import org.junit.Test;

import lts.Reporter_tests;
import lts.algorithms.Declared_fields;
import lts.webspace.sockets.Socket_client;
import lts.webspace.sockets.Socket_connected_to_server;
import lts.webspace.sockets.Socket_server;


/**
 * @see lts.webspace.sockets.Socket_server
 * @see lts.webspace.sockets.Socket_client
 */
public class Test_sockets {


    ////////// Variables //////////
    @Rule public Reporter_tests watchman = new Reporter_tests();


    ////////// Methods //////////
    @Test public void create() throws Exception {

        String ip = "127.0.0.1";
        Integer port = 3303;

        Socket_server socket_server = new Socket_server(port);
            socket_server.join();

        Socket_client socket_client = new Socket_client(ip, port);
            socket_client.join();

        Socket_connected_to_server socket_connected_to_server = socket_server._get_socket_connected_to_server();
            socket_connected_to_server.join();

            socket_client._send_msg("Hello, server)");
            socket_client._send_msg("Do you have everything under control?");

            socket_connected_to_server._send_msg("Hello, client)");
            socket_connected_to_server._send_msg("Yes, I have everything under control!");

        socket_client._stop(2000);
        socket_connected_to_server._stop(2000);
        socket_server._stop(2000);

        Declared_fields._get_list_all_currnet_threads();

    }

    @Test public void connecting_to_server_WORLD_TIME() throws Exception {

        String ip = "india.colorado.edu";
        Integer port = 13;

        Socket_client socket_client = new Socket_client(ip, port);
            socket_client.join();
            socket_client._stop(6000);

    }





}