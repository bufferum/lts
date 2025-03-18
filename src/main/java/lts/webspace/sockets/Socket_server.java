package lts.webspace.sockets;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lts.algorithms.Register_thread_id;
import lts.signs.Print;


/**
 * <h4>This class is used on the server side to receive and/or transmit data.</h4>
 *
 * @see lts.webspace.sockets.Socket_client
 * @version 2.0
 * @author bufferum
 */
public class Socket_server extends Thread {


    ////////// Variables //////////
    private Integer port;
    private String[] allow_ip_address;
    private String ip_client;
    private Integer thread_id;

    public volatile Boolean event_new_connection;
    private volatile Boolean working_server_socket;
    private volatile Boolean key_accept;
    private volatile Boolean key_closer;
    private ServerSocket server_socket = null;

    private Socket_connected_to_server socket_connected_to_server = null;
    private Thread thread_socket_server;


    ////////// Contstructors //////////
    @SuppressWarnings("unused")
    private Socket_server() { }

    /**
     * <h4>This constructor(thread) replaces the {@code start()} function
     * and will exist(wait) until the program calls the {@code _stop()} function.</h4>
     *
     * <p>All ip addresses are allowed.</p>
     */
    public Socket_server(Integer port) {

        this.port = port;
        this.allow_ip_address = (String[]) null;
        working_server_socket = true;
        event_new_connection = false;
        key_accept = false;
        key_closer = true;
        thread_id = new Register_thread_id()._get_thread_id();

        thread_socket_server = new Thread(() -> action());
            thread_socket_server.setName("thread_socket_server_action_" + thread_id);
            thread_socket_server.start();

        Thread thread_socket_server_listener_closer = new Thread(socket_server_listener_closer);
            thread_socket_server_listener_closer.setName("thread_socket_server_listener_closer_" + thread_id);
            thread_socket_server_listener_closer.start();

    }

    /**
     * <h4>This constructor(thread) replaces the {@code start()} function
     * and will exist(wait) until the program calls the {@code _stop()} function.</h4>
     *
     * @param allow_ip_address - The array of strings is just the ip addresses
     *                           of the clients that you can use to allow
     *                           them to connect to the server socket.
     */
    public Socket_server(Integer port, String[] allow_ip_address) {

        this.port = port;
        this.allow_ip_address = allow_ip_address;
        working_server_socket = true;
        event_new_connection = false;
        key_accept = false;
        key_closer = true;
        thread_id = new Register_thread_id()._get_thread_id();

        thread_socket_server = new Thread(() -> action());
            thread_socket_server.setName("thread_socket_server_action_" + thread_id);
            thread_socket_server.start();

        Thread thread_socket_server_listener_closer = new Thread(socket_server_listener_closer);
            thread_socket_server_listener_closer.setName("thread_socket_server_listener_closer_" + thread_id);
            thread_socket_server_listener_closer.start();

    }


    ////////// Methods //////////
    /**
     * <p>The function will permanently destroy all threads for the current
     * instance of this class.</p>
     *
     * @param delay_time - This is the time after which this method starts working.
     *                     This is useful in cases where you need to make sure
     *                     that all messages are processed and no new ones are
     *                     received.
     */
    public void _stop(long delay_time) {

        try { Thread.sleep(delay_time); }
        catch(InterruptedException e) { e.printStackTrace(); }

        working_server_socket = false;

        while(key_closer) { }

    }

    public Socket_connected_to_server _get_socket_connected_to_server() throws InterruptedException {

        while(!key_accept) { }
        key_accept = false;

        return socket_connected_to_server;
    }

    private void action() {

        try {

            event_new_connection = false;

            try { server_socket = new ServerSocket(port); }
            catch(IOException e) { e.printStackTrace(); }

            while(working_server_socket) {

                // Blocking the flow until some client makes a request.
                Socket socket = server_socket.accept();
                ip_client = socket.getInetAddress().getHostAddress();

                if(allow_ip_address != null) {

                    for(String allow_ip : allow_ip_address) {

                        if(allow_ip.equals(ip_client)) {

                            socket_connected_to_server = new Socket_connected_to_server(socket, thread_id);
                            key_accept = true;
                            event_new_connection = true;

                            break;
                        }

                    }

                }

                // All ip addresses are allowed
                else {

                    socket_connected_to_server = new Socket_connected_to_server(socket, thread_id);
                    key_accept = true;
                    event_new_connection = true;

                }

            }

        }
        catch(Throwable e) { }

    }

    /**
     * <p>This thread is necessary to disable the rest of the threads.
     * Because
     * <ul>
     *      <li>{@code reader}
     *      <li>{@code writer}
     *      <li>{@code message_queue}
     *      <li>{@code socket.accept()}
     * </ul>
     *
     * they can all block the streams they are in.</p>
     *
     * <p>This listener disables everything. Data loss is possible, for example,
     * if the socket catches a signal when disconnecting.</p>
     */
    private Runnable socket_server_listener_closer = () -> {

        Boolean key = true;

        while(key) {

            if(!working_server_socket) {

                try { server_socket.close(); }
                catch(IOException e) { e.printStackTrace(); }

                thread_socket_server.interrupt();

                Print.result("\n[SERVER] - Disabled!");

                key = false;
                key_accept = true;
                key_closer = false;

            }

        }

    };


}