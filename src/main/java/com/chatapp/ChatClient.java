 package com.chatapp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

    public class ChatClient {
        private String serverAddress;
        private int serverPort;

        public ChatClient(String address, int port) {
            this.serverAddress = address;
            this.serverPort = port;
        }

        public void start() {
            try (Socket socket = new Socket(serverAddress, serverPort);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 Scanner scanner = new Scanner(System.in)) {

                System.out.println("Подключено к чату на сервере " + serverAddress + ":" + serverPort);


                new Thread(new IncomingMessagesHandler(in)).start();


                while (true) {
                    String message = scanner.nextLine();
                    if (message.equalsIgnoreCase("exit")) {
                        break;
                    }
                    out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Ошибка подключения к серверу.");
                e.printStackTrace();
            }
        }

        private static class IncomingMessagesHandler implements Runnable {
            private BufferedReader in;

            public IncomingMessagesHandler(BufferedReader in) {
                this.in = in;
            }

            @Override
            public void run() {
                String message;
                try {
                    while ((message = in.readLine()) != null) {
                        System.out.println("Сообщение: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка при получении сообщений от сервера.");
                    e.printStackTrace();
                }
            }
        }


        }



