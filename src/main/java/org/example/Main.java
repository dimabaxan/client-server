package org.example;


import com.chatapp.ChatClient;

import java.io.IOException;
import java.net.ServerSocket;

import static sun.management.jmxremote.ConnectorBootstrap.PropertyNames.PORT;

     public class Main {
           public static void main(String[] args) {
               System.out.println("Serverul a pornit și așteaptă conexiuni...");
               try ( ServerSocket serverSocket = new ServerSocket(12345)) {
                   while (true) {
                       new ClientHandler(serverSocket.accept()).start();
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
               ChatClient client = new ChatClient("127.0.0.1", 12345);

           }


