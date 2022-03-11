package com.company.topicThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    static ArrayList<Client> clients = new ArrayList<>();
    ServerSocket server;

    ChatServer() throws IOException{
        // создаем серверный сокет на порту 1234
        server = new ServerSocket(1234);
    }

    void messAllPersons(String str){
        for(Client client : clients){
            client.receiveMessage(str);
        }
    }
    public void run(){
        try {
            while(true) {
                Socket socket = server.accept();
                clients.add(new Client(socket, this));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
