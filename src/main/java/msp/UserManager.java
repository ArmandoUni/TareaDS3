package msp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class UserManager {

    public static final String CLASS_NAME = UserManager.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    private HashMap<String, Socket> connections;

    public UserManager() {
        super();
        connections = new  HashMap<String, Socket>();
    }

    public boolean connect(String user, Socket socket) {
        boolean result = true;

        Socket s = connections.put(user,socket);
        if( s != null) {
            result = false;
        }
        return result;
    }

    public boolean disconnect(String user, Socket socket){

        boolean s = connections.remove(user,socket);

        return s;
    }

    public Socket get(String user) {

        Socket s = connections.get(user);

        return s;
    }

    public void send(String message, String user) {

        Socket s = connections.get(user);

        if (!(s == null)) {
            try {
                PrintWriter output = new PrintWriter(s.getOutputStream(), true);
                output.println(message);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
                e.printStackTrace();
            }
        }

        /*
        Collection<Socket> conexiones = connections.values();

        for (Socket s : conexiones) {
            try {
                PrintWriter output = new PrintWriter(s.getOutputStream(), true);
                output.println(message);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
                e.printStackTrace();
            }
        }
         */
    }

    public void list(String user) {

        Set<String> hs = connections.keySet();
        Socket socket = get(user);
        String lista = "";

        for (String s: hs) {
            if (lista == "") {
                lista = s;
            } else {
                lista = lista + ", " + s;
            }
        }
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(lista);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
        }
    }
}
