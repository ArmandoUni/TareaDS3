package echo;
import datetime.DatetimeServer;

import java.net.*;
import java.io.*;
import java.util.logging.Logger;

public class EchoServer {
    public static final String CLASS_NAME = EchoServer.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    public static final int PORT = 7777;

    public static void main(String[] args) {

        int portNumber = 0;
        if (args.length == 0) {
            System.err.println("Usage: java EchoServer <port number>");
            //System.exit(1);
            portNumber = PORT;
        } else {
            portNumber = Integer.parseInt(args[0]);
        }

        try {
            ServerSocket serverSocket
                    = new ServerSocket(portNumber);

            System.out.println("Esperando a cliente...");

            Socket clientSocket = serverSocket.accept();

            PrintWriter out
                    = new PrintWriter( clientSocket.getOutputStream() , true);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader( clientSocket.getInputStream() ));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if( inputLine.trim().equals(".") ) {
                    // out.println(inputLine);
                    break;
                } else {
                    out.println(inputLine);
                }
            }
            clientSocket.close();
        } catch (IOException e) {
            LOGGER.severe("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            LOGGER.severe(e.getMessage());
        }
    }

}
