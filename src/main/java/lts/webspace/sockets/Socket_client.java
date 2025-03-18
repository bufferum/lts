package lts.webspace.sockets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import lts.signs.Print;


/**
 * <h4>This class is used on the client side to receive and/or transmit data.</h4>
 *
 * <p>To prevent the data from being displayed in the terminal - use {@code Print.printer_enable/disable()}</p>
 *
 * @see lts.webspace.sockets.Socket_server
 * @version 2.0
 * @author bufferum
 */
public class Socket_client extends Thread { // implements Interface_client


    ////////// Variables //////////
    private String ip;
    private Integer port;
    private String ip_client;

    private volatile Boolean working_socket_client;
    private volatile Boolean key_closer;
    private PrintWriter writer;
    private BufferedReader reader;
    private BlockingQueue<String> queue_output;
    private Socket socket;

    private Thread thread_socket_client;
    private Thread thread_socket_client_listener_reader;
    private Thread thread_socket_client_listener_writer;


    ////////// Contstructors //////////
    @SuppressWarnings("unused")
    private Socket_client() { }

    /**
     * This constructor(thread) replaces the {@code start()} function
     * and will exist(wait) until the program calls the {@code _stop()} function.
     */
    public Socket_client(String ip, Integer port) {

        this.ip = ip;
        this.port = port;
        key_closer = true;
        working_socket_client = true;
        queue_output = new LinkedBlockingQueue<>();

        thread_socket_client = new Thread(() -> action());
            thread_socket_client.setName("thread_socket_client_action_" + ip_client);
            thread_socket_client.start();

        Thread thread_socket_client_listener_closer = new Thread(socket_client_listener_closer);
            thread_socket_client_listener_closer.setName("thread_socket_client_listener_closer_" + ip_client);
            thread_socket_client_listener_closer.start();

    }


    ////////// Methods //////////
    private void action() {

        try {

            socket = new Socket(ip, port);
            ip_client = socket.getInetAddress().getHostAddress();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            thread_socket_client_listener_reader = new Thread(socket_client_listener_reader);
                thread_socket_client_listener_reader.setName("thread_socket_client_listener_reader_" + ip_client);
                thread_socket_client_listener_reader.start();

            thread_socket_client_listener_writer = new Thread(socket_client_listener_writer);
                thread_socket_client_listener_writer.setName("thread_socket_client_listener_writer_" + ip_client);
                thread_socket_client_listener_writer.start();

            while(working_socket_client) { }

        }
        catch(Throwable e) { e.printStackTrace(); }

    }

    /** The function sends a text to the server. */
    public void _send_msg(String msg) {

        try { queue_output.put(msg); }
        catch(InterruptedException e) { e.printStackTrace(); }

    }

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

        while(!queue_output.isEmpty()) { }

        working_socket_client = false;

        while(key_closer) { }

    }

    protected Runnable socket_client_listener_reader = () -> {

        while(working_socket_client) {

            try {

                String line;
                while((line = reader.readLine()) != null) {

                    Print.result("\n[SERVER]: " + line);

                }

            }
            catch(IOException e) { }

        }

    };

    private Runnable socket_client_listener_writer = () -> {

        while(working_socket_client) {

            try {

                String msg = queue_output.poll(100, TimeUnit.MILLISECONDS);

                if(msg != null) {
                    writer.println(msg);
                }

            }
            catch(InterruptedException e) { }

        }

    };

    /**
     * <p>This thread is necessary to disable the rest of the threads.</p>
     * Because
     * <ul>
     *      <li>{@code reader}
     *      <li>{@code writer}
     *      <li>{@code queue_output}
     *      <li>{@code socket.accept()}
     * </ul>
     *
     * they can all block the streams they are in.</p>
     *
     * <p>This listener disables everything. Data loss is possible, for example,
     * if the socket catches a signal when disconnecting.</p>
     */
    private Runnable socket_client_listener_closer = () -> {

        Boolean key = true;

        while(key) {

            if(!working_socket_client) {

                while(!queue_output.isEmpty()) { }

                queue_output.clear();
                thread_socket_client_listener_reader.interrupt();
                thread_socket_client_listener_writer.interrupt();
                thread_socket_client.interrupt();

                try {

                    socket.close();
                    reader.close();
                    writer.close();

                }
                catch(IOException e) { e.printStackTrace(); }

                Print.result("\n[CLIENT] - Disabled!");

                key = false;
                key_closer = false;

            }

        }

    };


}