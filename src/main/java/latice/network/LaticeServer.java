package latice.network;

import java.io.*;
import java.net.*;
import java.util.*;

public class LaticeServer {
    private ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();

    public void demarrer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Serveur démarré sur le port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Nouveau client connecté !");
            ClientHandler handler = new ClientHandler(clientSocket, this);
            clients.add(handler);
            new Thread(handler).start();
        }
    }

    public void diffuser(Commande commande) {
        for (ClientHandler client : clients) {
            client.envoyer(commande);
        }
    }
}
