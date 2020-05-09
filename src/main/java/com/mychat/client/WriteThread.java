package com.mychat.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {
    private Socket socket;
    private ChatClient client;
    private PrintWriter writer;

    WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.print("Enter your name : ");
        Scanner in = new Scanner(System.in);
        String userName = in.nextLine();
        client.setUserName(userName);
        writer.println(userName);
        String text;

        do {
            System.out.print("[" + client.getUserName() + "] : ");
            text = in.nextLine();
            writer.println(text);
        } while (!text.equals("bye"));


        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error writing to server: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
