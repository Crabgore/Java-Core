package Lesson_6.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class MainServer {

    private static Vector<ClientHandler> clients;

    public MainServer() throws SQLException {
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            AuthService.connect();

            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(socket, this);
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
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    // подписываем клиента на рассылку
    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    // отписываем клиента от рассылки сообщений
    public void unsubscribe(ClientHandler client){
        clients.remove(client);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    public void privateMsg (String from, String client, String msg){
        for (ClientHandler o: clients) {
            if (o.getNick().equals(client)){
                o.sendMsg(from + ": " + msg);
            }
            if (o.getNick().equals(from)){
                o.sendMsg("to: " + client + " " + msg);
            }
        }
    }

    public static boolean isAlreadyAuth(String nick){
        boolean result = false;
        for (ClientHandler o: clients) {
            if (o.getNick().equals(nick)){
                result = true;
            }
        }
        return result;
    }
}
