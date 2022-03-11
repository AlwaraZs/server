package com.company.topicThrows;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class Client implements Runnable {
    Socket socket;
    ChatServer server;
    PrintStream out;

    Client(Socket socket, ChatServer server){
        this.socket = socket;
        this.server = server;
        new Thread(this).start();
    }

    void receiveMessage(String str){
        out.println(str);
    }

    public void run() {
        String name;
        try {
            // читаем из сети и пишем в сеть
            Scanner in = new Scanner(socket.getInputStream());
            out = new PrintStream(socket.getOutputStream());
            out.println("Welcome to mountains!\n help:\n1) to disconnect white <disconnect>\n2) respect the other participants in the conversation\nPls write you NAME");
            name = in.nextLine();
            server.messAllPersons(String.format("Client %s joined the chat :)", name));
            String input = in.nextLine();
            while (!input.equals("disconnect")) {
                server.messAllPersons(String.format("%s: %s", name, input));
                input = in.nextLine();
            }
            server.messAllPersons(String.format("Client %s left the chat :( RIP", name));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}