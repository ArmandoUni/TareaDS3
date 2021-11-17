package datetime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Logger;

public class DatetimeServer {

    public static final String CLASS_NAME = DatetimeServer.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java DatetimeServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try {
            // Ligar el servicio al puerto
            ServerSocket serverSocket
                    = new ServerSocket(portNumber);
            LOGGER.info("Server running on port: " + portNumber);

            // Esperar conexiones
            Socket clientSocket = null;

            int i = 0;
            while (i < 5) { // solo atiende a 5 clientes y termina el programa
                clientSocket = serverSocket.accept();
                LOGGER.info("Connection accepted.");

                // Flujo de salida (enviar datos al cliente)
                PrintWriter out
                        = new PrintWriter(clientSocket.getOutputStream(), true);

                // Flujo de entrada (leer datos que envia el cliente)
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));


                Date now = new Date();

                out.println(now.toString());
                clientSocket.close();
                i++;
            }
            LOGGER.info("Server stopped.");
        } catch (IOException e) {
            LOGGER.severe("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            LOGGER.severe(e.getMessage());
        }
    }
}
