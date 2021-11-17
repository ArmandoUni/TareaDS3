package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoService implements Runnable {

    private Socket clientSocket = null;

    public EchoService(Socket client) {
        clientSocket = client;
    }

    @Override
    public void run() {

        BufferedReader in
                = null;
        try {
            PrintWriter out
                    = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.trim().equals(".")) {
                    break;
                }
                out.println(inputLine);
            }
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println( "Error en la conexion");
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(EchoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
