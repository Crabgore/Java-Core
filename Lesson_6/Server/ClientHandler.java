package Lesson_6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private MainServer server;
    private String nick;

    public ClientHandler(Socket socket, MainServer server) {
        try {
            this.socket = socket;
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        // цикл для авторизации
                        while (true) {
                            String str = in.readUTF();
                            // если сообщение начинается с /auth
                            if(str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                // Вытаскиваем данные из БД
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (newNick != null && !MainServer.isAlreadyAuth(newNick)) {
                                    // отправляем сообщение об успешной авторизации
                                    sendMsg("/authok");
                                    nick = newNick;
                                    server.subscribe(ClientHandler.this);
                                    break;
                                } else if(newNick == null){
                                    sendMsg("Неверный логин/пароль!");
                                } else if(MainServer.isAlreadyAuth(newNick)){
                                    sendMsg("Пользователь уже авторизован!");
                                }
                            }
                        }

                        // блок для отправки сообщений
                        while (true) {
                            String str = in.readUTF();
                            if(str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            if (str.startsWith("/w")) {
                                StringBuilder builder = new StringBuilder();
                                String[] strings = str.split(" ");
                                for (String s: strings) {
                                    builder.append(s + " ");
                                }
                                // 4 дополнительных символа это "/w" c пробелом и пробел после ника
                                builder.delete(0, (nick.length()+4));
                                server.privateMsg(nick, strings[1], builder.toString());
                            } else server.broadcastMsg(nick + ": "+ str);
                        }

                    }  catch (IOException e) {
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

    public String getNick (){
        return this.nick;
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
