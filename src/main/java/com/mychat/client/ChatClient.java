package com.mychat.client;

import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    private String host;
    private int port;
    private String userName;

    ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void execute() {
        try {
            final var socket = new Socket(host, port);

            System.out.println("Connected to the chat server");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return this.userName;
    }

    public static void main(String[] args) {
        System.out.println("Client started");
        ChatClient client = new ChatClient("127.0.0.1", 1234);
        client.execute();
    }

}
