package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientsHolder {

    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private Server server;
    private Socket socket;

    public ClientsHolder(Socket socket, Server server) {
        try {
            this.server = server;
            this.socket = socket;
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String string = inputStream.readUTF();
                            if (string.equals("/end")) {
                                break;
                            }
                            server.broadcastMessage("Client: " + string);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMsg(String string) {
        try {
            outputStream.writeUTF(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
