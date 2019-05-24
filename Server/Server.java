package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    Vector<ClientsHolder> clients;

    public Server(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен");

            while (true) {
                socket = serverSocket.accept();
                System.out.println("Клиент подключён");
                clients.add(new ClientsHolder(socket, this));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void broadcastMessage (String string) {
        for (ClientsHolder o: clients) {
            o.sendMsg(string);

        }
    }
}
