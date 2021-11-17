package datetime;

import java.net.*;
import java.io.*;
import java.util.logging.Logger;

public class ConcurrentDatetimeServer {

    public static final String CLASS_NAME = DatetimeServer.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    public static void main(String[] args)  {

        if (args.length != 1) {
            System.err.println("Usage: java ConcurrentDatetimeServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try {
            // Ligar el servicio al puerto
            ServerSocket serverSocket
                    = new ServerSocket( Integer.parseInt(args[0]) );
            LOGGER.info("Server running on port: " + portNumber);

            // Esperar conexiones
            Socket clientSocket = null;

            int i = 0;
            while (i < 5) {
                clientSocket = serverSocket.accept();
                LOGGER.info("Connection accepted.");
                // Flujo de salida (enviar datos al cliente)
                Thread service = new Thread( new DateService(clientSocket) );

                service.start();
                i++;
            }

            LOGGER.info("Server stopped.");

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }

    }
}
