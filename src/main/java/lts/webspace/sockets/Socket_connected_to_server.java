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
 * <h4>This class is needed for pool sockets to work.
 * This class stores streams with listeners for client sockets.</h4>
 *
 * @see lts.webspace.sockets.Socket_server
 * @version 2.0
 * @author bufferum
 */
public class Socket_connected_to_server extends Thread {


    ////////// Variables //////////
    private Integer thread_id;

    private volatile Boolean working_socket_connected_to_server;
    private volatile Boolean key_closer;
    private Socket socket;
    private PrintWriter writer = null;
    private BufferedReader reader = null;
    private BlockingQueue<String> queue_input;

    private Thread thread_socket_connected_to_server;
    private Thread thread_socket_connected_to_server_listener_reader;
    private Thread thread_socket_connected_to_server_listener_writer;


    ////////// Constructors //////////
    @SuppressWarnings("unused")
    private Socket_connected_to_server() { }

    public Socket_connected_to_server(Socket socket, Integer thread_id) {

        working_socket_connected_to_server = true;
        this.socket = socket;
        this.thread_id = thread_id;
        key_closer = true;

        thread_socket_connected_to_server = new Thread(() -> action());
            thread_socket_connected_to_server.setName("thread_socket_connected_to_server_action_" + thread_id);
            thread_socket_connected_to_server.start();

        Thread thread_socket_connected_to_server_listener_closer = new Thread(socket_connected_to_server_listener_closer);
            thread_socket_connected_to_server_listener_closer.setName("thread_socket_connected_to_server_listener_closer_" + thread_id);
            thread_socket_connected_to_server_listener_closer.start();

    }


    ////////// Methods //////////
    /** The function sends a text to the client. */
    public void _send_msg(String msg) {

        try { queue_input.put(msg); }
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

        while(!queue_input.isEmpty()) { }

        working_socket_connected_to_server = false;

        while(key_closer) { }

    }

    private void action() {

        try {

            queue_input = new LinkedBlockingQueue<>();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

        }
        catch(IOException e) { e.printStackTrace(); }

        thread_socket_connected_to_server_listener_reader = new Thread(socket_server_listener_reader);
            thread_socket_connected_to_server_listener_reader.setName("thread_socket_connected_to_server_listener_reader_" + thread_id);
            thread_socket_connected_to_server_listener_reader.start();

        thread_socket_connected_to_server_listener_writer = new Thread(socket_server_listener_writer);
            thread_socket_connected_to_server_listener_writer.setName("thread_socket_connected_to_server_listener_writer_" + thread_id);
            thread_socket_connected_to_server_listener_writer.start();

    }

    private Runnable socket_server_listener_reader = () -> {

        while(working_socket_connected_to_server) {

            try {

                String line;
                while((line = reader.readLine()) != null) {

                    Print.test("\n[CLIENT]: " + line);

                }

            }
            catch(IOException e) { }

        }

    };

    private Runnable socket_server_listener_writer = () -> {

        while(working_socket_connected_to_server) {

            try {

                String msg = queue_input.poll(100, TimeUnit.MILLISECONDS);

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
     *      <li>{@code queue_input}
     *      <li>{@code socket.accept()}
     * </ul>
     *
     * they can all block the streams they are in.</p>
     *
     * <p>This listener disables everything. Data loss is possible, for example,
     * if the socket catches a signal when disconnecting.</p>
     */
    private Runnable socket_connected_to_server_listener_closer = () -> {

        Boolean key = true;

        while(key) {

            if(!working_socket_connected_to_server) {

                while(!queue_input.isEmpty()) { }

                thread_socket_connected_to_server_listener_reader.interrupt();
                thread_socket_connected_to_server_listener_writer.interrupt();
                thread_socket_connected_to_server.interrupt();

                try {

                    queue_input.clear();
                    reader.close();
                    writer.close();
                    socket.close();

                }
                catch(IOException e) { e.printStackTrace(); }

                Print.result("\n[SCTS] - Disabled!");

                key = false;
                key_closer = false;


            }

        }

    };


}