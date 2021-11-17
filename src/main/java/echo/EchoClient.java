package echo;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class EchoClient {

    public static final String CLASS_NAME = EchoClient.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    public static final int PORT = 7777;

    public static void main(String[] args) {

        String hostName = null;
        int portNumber = 7777;

        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            hostName = "localhost";
            portNumber = 7777;
            // System.exit(1);
        } else {
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
        }

        try {
            Socket echoSocket = new Socket(hostName, portNumber);

            PrintWriter out
                    = new PrintWriter( echoSocket.getOutputStream() , true);

            BufferedReader in
                    = new BufferedReader(
                    new InputStreamReader( echoSocket.getInputStream() ));

            BufferedReader teclado
                    = new BufferedReader(
                    new InputStreamReader(System.in));

            String userInput;
            while ((userInput = teclado.readLine()) != null) {
                if( userInput.trim().equals(".") ) {
                    out.println(userInput);
                    break;
                }   else {
                    out.println(userInput);
                }
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }

    }
}
