package com.company.topicThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket server;

    ChatServer() throws IOException{
        // создаем серверный сокет на порту 1234
        server = new ServerSocket(1234);
    }

    void messAllPersons(String str){
        for(Client client : clients){
            if (client != null) client.receiveMessage(str);
        }
    }
    public void run(){
        try {
            while(true) {
                clients.add(new Client(server.accept(), this));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
