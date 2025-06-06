package latice.network;

import java.io.*;
import java.net.*;

public class LaticeClient {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void connecter(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        new Thread(() -> {
            try {
                while (true) {
                    Commande commande = (Commande) in.readObject();
                    System.out.println("Commande reçue du serveur : " + commande.getType());
                    // TODO : traiter les commandes ici
                }
            } catch (Exception e) {
                System.out.println("Déconnecté du serveur.");
            }
        }).start();
    }

    public void envoyer(Commande commande) {
        try {
            out.writeObject(commande);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}