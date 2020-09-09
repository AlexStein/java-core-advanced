package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class Server {
    private List<ClientHandler> clients;
    private AuthService authService;

    private final int PORT = 8189;
    ServerSocket server = null;
    Socket socket = null;

    public Server() {
        clients = new Vector<>();
        authService = new SimpleAuthService();

        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");

                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    /**
     * Отправка общего сообщения пользователя
     *
     * @param sender Отправитель
     * @param msg    Текст сообщения
     */
    public void broadcastMsg(ClientHandler sender, String msg) {
        String message = String.format("%s : %s", sender.getNickname(), msg);
        for (ClientHandler c : clients) {
            if (!c.equals(sender)) {
                c.sendMsg(message);
            }
        }
        // Сообщение отправителю
        sender.sendMsg(String.format("Я : %s", msg));
    }

    /**
     * Сообщения сервера
     *
     * @param msg Текст информационного сообщения
     */
    public void broadcastServerMsg(String msg) {
        String message = String.format("* %s *", msg);
        for (ClientHandler c : clients) {
            c.sendMsg(message);
        }
    }

    /**
     * Отправка личного сообщения пользователю
     *
     * @param sender         Отправитель
     * @param targetNickname Имя получателя
     * @param msg            Текст сообщения
     */
    public void privateMsg(ClientHandler sender, String targetNickname, String msg) {
        boolean sentSuccessfully = false;

        for (ClientHandler c : clients) {
            if (c.getNickname().equals(targetNickname)) {
                String message = String.format("Личное от %s : %s", sender.getNickname(), msg);
                c.sendMsg(message);
                sentSuccessfully = true;
                break;
            }
        }

        // Сообщение отправителю
        String message = String.format("Я : (личное для %s) %s", targetNickname, msg);
        if (!sentSuccessfully) {
            message = String.format("Пользователь %s не найден", targetNickname);
        }
        sender.sendMsg(message);
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

}
