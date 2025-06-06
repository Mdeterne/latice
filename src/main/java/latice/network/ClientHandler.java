package latice.network;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private LaticeServer serveur;

    public ClientHandler(Socket socket, LaticeServer serveur) {
        this.socket = socket;
        this.serveur = serveur;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void envoyer(Commande commande) {
        try {
            out.writeObject(commande);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Commande commande = (Commande) in.readObject();
                System.out.println("Commande reçue : " + commande.getType());
                serveur.diffuser(commande); // relaye à tous
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client déconnecté.");
        }
    }
}