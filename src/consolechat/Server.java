package consolechat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Сервер консольного чата
 */
public class Server {

    // Порт прослушивания сервером
    private int PORT = 8189;

    // Потоки ввода / вывода
    private DataInputStream data_in;
    private DataOutputStream data_out;

    public Server() {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");

            socket = server.accept();
            System.out.println("Клиент подключился");

            try {
                data_in = new DataInputStream(socket.getInputStream());
                data_out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

            // Поток чтения сообщений
            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        String str = data_in.readUTF();

                        if (str.equals("/end")) {
                            System.out.println("Клиент отключается");
                            break;
                        }

                        System.out.println("Клиент: " + str);
                    }
                } catch (IOException e) {
                    System.out.println("Клиент разорвал соединение");
                }

                System.out.println("Клиент отключился");
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

                        System.out.println("Сервер останавливается");
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
                //Thread.yield();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Сервер остановлен");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
