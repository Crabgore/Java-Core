package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    Socket socket = null;
    DataInputStream in;
    DataOutputStream out;
    Server server;

    public String getNick() {
        return nick;
    }

    String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());



            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if(str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if(newNick != null) {
                                    if(!server.isNickBusy(newNick)) {
                                        sendMsg("/authok");
                                        nick = newNick;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    } else {
                                        sendMsg("Учетная запись уже используется!");
                                    }
                                } else {
                                    sendMsg("Неверный логин/пароль!");
                                }
                            }
                        }

                        out.writeUTF(AuthService.getHistory(nick).toString());

                        while (true) {
                            String str = in.readUTF();
                            if (!str.startsWith("/")) AuthService.addHistory(nick, str);
                            if(str.startsWith("/")) {
                                if(str.equals("/end")) {
                                    out.writeUTF("/serverClosed");
                                }
                                if(str.startsWith("/w ")) {
                                    String[] tokens = str.split(" ",3);
                                    if (!tokens[1].equals(AuthService.getBlacklistByNick(nick))){
                                        server.sendPersonalMsg(ClientHandler.this, tokens[1], tokens[2]);
                                    } else sendMsg("Пользователь " + tokens[1] + "добавил вас в чёрный список. Увы.");
                                }
                                if(str.startsWith("/blacklist ")) {
                                    String[] tokens = str.split(" ");
                                    AuthService.addToBlacklist(nick, tokens[1]);
                                    sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                                }
                            } else {
                                server.broadcastMsg(ClientHandler.this,nick + ": " + str);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkBlackList(String to, String from) {
        if (AuthService.getNickByBlacklist(to) != null && from.equals(AuthService.getNickByBlacklist(to))){
            return true;
        } else return false;
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
