package consolechat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Клиент консольного чата
 */
public class Client {

    // Параметры сервера
    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;

    // Сокет соединения с сервером
    private Socket socket;

    // Потоки ввода / вывода
    private DataInputStream data_in;
    private DataOutputStream data_out;

    public Client() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);

            data_in = new DataInputStream(socket.getInputStream());
            data_out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Подключились к серверу");

            // Поток чтения сообщений
            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        String str = data_in.readUTF();

                        if (str.equals("/end")) {
                            System.out.println("Сервер отключается");
                            break;
                        }

                        System.out.println("Сервер: " + str);
                    }
                } catch (IOException e) {
                    System.out.println("Сервер разорвал соединение");
                    // e.printStackTrace();

                } finally {

                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });

            // Поток для отправки сообщений
            Thread sendThread = new Thread(() -> {
                Scanner type_in = new Scanner(System.in);

                while (true) {
                    String str = type_in.nextLine();

                    if (str.equals("/end")) {
                        try {
                            data_out.writeUTF(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Клиент отключается");
                        break;
                    }

                    try {
                        data_out.writeUTF(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            readThread.setDaemon(true);
            readThread.start();

            sendThread.setDaemon(true);
            sendThread.start();

            // Главный поток работает, пока есть соединение и оба потока живы
            while (sendThread.isAlive() && readThread.isAlive()) {
                // Thread.yield();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка соединения с сервером: " + IP_ADDRESS + ":" + PORT);
            // e.printStackTrace();

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Клиент остановлен");
    }

    public static void main(String[] args) {
        new Client();
    }
}
