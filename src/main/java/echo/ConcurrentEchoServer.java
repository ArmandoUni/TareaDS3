package echo;

import java.net.*;
import java.io.*;
public class ConcurrentEchoServer {
    public static void main(String[] args)  {
        int portNumber = 7777;
        if (args.length != 1) {
            System.err.println("Usage: java ConcurrentEchoServer <port number>");
            portNumber = 7777;
            //System.exit(1);
        } else {
            portNumber = Integer.parseInt(args[0]);
        }
        try {
            ServerSocket serverSocket
                    = new ServerSocket( portNumber );

            //
            while( true ) {
                Socket clientSocket = serverSocket.accept();

                Thread serviceProcess = new Thread(new EchoService(clientSocket));

                serviceProcess.start();
            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
