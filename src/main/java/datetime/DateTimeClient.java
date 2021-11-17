package datetime;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class DateTimeClient {

    public static final String CLASS_NAME = DateTimeClient.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    public static void main(String[] args) {


       if (args.length != 2) {
           System.err.println(
                    "Usage: java DateTimeClient <host name> <port number>");
            System.exit(1);
       }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try {
            // abrir la conexion con el servidor
            //Socket theSocket = new Socket("127.0.0.1", 1313);
            Socket theSocket = new Socket(hostName, portNumber);

            // Obtener flujo de salida
            PrintWriter out
                    = new PrintWriter( theSocket.getOutputStream(), true);

            // Obtener flujo de entrada
            BufferedReader in
                    = new BufferedReader(
                    new InputStreamReader( theSocket.getInputStream() ));

            // leer la fecha que envia el servidor
            String dateTimeString = in.readLine();

            //imprimir el valor en consola
            System.out.println(dateTimeString);

            theSocket.close();

        } catch (UnknownHostException e) {
            LOGGER.severe("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            LOGGER.severe("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }
}
