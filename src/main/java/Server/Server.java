package Server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class Server {

    private Logger loggerFile = Logger.getLogger("file");
    Logger loggerAdmin = Logger.getLogger("admin");

    private Vector<ClientHandler> clients;

    public Server() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            AuthService.connect();
//            AuthService.addUser("login1", "pass1", "nick1");
//            AuthService.addUser("login2", "pass2", "nick2");
//            AuthService.addUser("login3", "pass3", "nick3");
            server = new ServerSocket(8189);
            loggerFile.info("Сервер запущен!");
            loggerAdmin.info("Сервер запущен!");

            while (true) {
                socket = server.accept();
                loggerFile.info("Клиент подключился");
                loggerAdmin.info("Клиент подключился");
                new ClientHandler(this,socket);
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

    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientList();
    }

    public void broadcastMsg(ClientHandler from, String msg) {
        for (ClientHandler o: clients) {
            if(!o.checkBlackList(o.getNick(), from.getNick())) {
                o.sendMsg(msg);
                loggerFile.info(from + " написал сообщение " + msg);
                loggerAdmin.info(from + " написал сообщение " + msg);
            }
        }
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler o: clients) {
            if(o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o: clients) {
            if(o.getNick().equals(nickTo)) {
                o.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                loggerAdmin.info(from + " написал личное сообщение " + nickTo + " " + msg);
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + " не найден!");
    }

    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientlist ");

        for (ClientHandler o : clients) {
            sb.append(o.getNick() + " ");
        }

        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }
}
