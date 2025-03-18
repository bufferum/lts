
# Sockets

[⬅️LTS](../../README.md)

## Socket_client

### An example of using a client socket

There is a server `india.colorado.edu` which returns the world time. Its connection port is `13`.

That's why we connect to this server socket:

```java
private static final String ip = "india.colorado.edu";
private static final Integer port = 13;

public static void main(String[] args) throws InterruptedException {

    Socket_client socket_client = new Socket_client(ip, port);
        socket_client.join();
        socket_client._stop(6000);

}
```

And we get the result in the console:

```planetext
[SERVER]:
[SERVER]: 60751 25-03-17 15:04:00 50 0 0 205.6 UTC(NIST) *
```

### Getting results from the server

```java
Socket_client_core socket_client = new Socket_client_core(ip, port);
```

This code calls the `socket_client_listener_reader` thread, which outputs the input stream to the console on its own.

At the moment, the code cannot write the results to variables. Only the output to the console.

### Sending messages to the server

```java
private static final String ip = "127.0.0.1";
private static final Integer port = 3301;

public static void main(String[] args) throws InterruptedException {

    Socket_client_core socket_client = new Socket_client_core(ip, port);

        socket_client._send_msg("Hello, server)");

        socket_client._stop(3000);

}
```

You can send this message if there is a server socket on this `ip` and `port`. There will be code examples later on how to create it.

But the answer will be this:

```planetext
[CLIENT]: Hello, server)
```

## Socket_server

The following code uses the idea of ___pool connection___. After all, if your server socket receives more than one connection, then multithreading is essential.

```java
private static final Integer port = 3301;

public static void main(String[] args) throws InterruptedException {

    Socket_server socket_server = new Socket_server(port);
    Socket_connected_to_server socket_connected_to_server = socket_server._get_socket_connected_to_server();
        socket_connected_to_server.join();

        // Sending your messages
        socket_connected_to_server._send_msg("Hello, client)");

        // Disabling all streams. Shutting down sockets.
        socket_connected_to_server._stop(1000);
        socket_server._stop(1000);

}
```

```java
Socket_server socket_server = new Socket_server(port); // This is used once in your Main.
```

Then, when a client socket tries to connect to your server socket, create a separate thread for each client in the form of the `Socket_connected_to_server` class.

```java
private static final Integer port = 3303;

public static void main(String[] args) throws InterruptedException {

    // You need to come up with the logic yourself, how to choose the right key that will turn off the computer.
    Boolean key = true;
    Socket_server socket_server = new Socket_server(port);
        socket_server.join();

    while(key) {

        // The program will enter the loop if socket_server detects a connection from a new client
        while(socket_server.event_new_connection) {

            new Thread(() ->

                {

                    try {

                        Socket_connected_to_server socket_connected_to_server = socket_server._get_socket_connected_to_server();
                            socket_connected_to_server.join();
                            socket_connected_to_server._send_msg("Hello, client)");
                            socket_connected_to_server._stop(10000);

                    }
                    catch(InterruptedException e) { e.printStackTrace(); }

                }, "Thread_new_client_" + Register_thread_id._get_thread_id()

            ).start();

            // During this delay, event_new_connection should change.
            Thread.sleep(10000);

        }

    }

    socket_server._stop(1000);

}
```

> This is an example of a multithreaded server socket implementation.

## Function `_stop()`

Secrets in this library are disabled when their code reaches the end.
The main class starts the read, write, and disconnect threads (only the `_stop` function).

If you do not call the `_stop` function, then the threads will run indefinitely.

If you don't need to constantly communicate with the server, but just send and immediately receive a response, then it makes sense to call the `_stop` function at the end of the code.
There's nothing stopping you from recreating your client socket, especially when it requires so little code to run.

But for safety, I recommend putting 10,000 milliseconds in the `_stop` function parameter, since it is better to give the system some time so that all messages from the stream are exhausted and no new messages get into the session.
