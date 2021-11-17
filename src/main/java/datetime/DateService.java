package datetime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Logger;

public class DateService implements Runnable {

    public static final String CLASS_NAME = DateService.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    private Socket clientSocket;

    public DateService(Socket c) {
        clientSocket = c;
    }
    @Override
    public void run() {
        PrintWriter out  = null;
        try {
            out  = new PrintWriter( clientSocket.getOutputStream(), true);
            // Flujo de entrada (leer datos que envia el cliente)
            BufferedReader in = new BufferedReader(
                    new InputStreamReader( clientSocket.getInputStream() ));

            Date now = new Date();

            out.println( now.toString() );
            clientSocket.close();
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        } finally {
            out.close();
        }

    }
}
