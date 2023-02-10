package org.client.net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private InputStream inStream;
    private OutputStream outStream;

    public ClientHandler(String ip, int port) {
        try {
            this.socket = new Socket(InetAddress.getByName(ip), port);
            this.socket.setTcpNoDelay(true);
            this.inStream = socket.getInputStream();
            this.outStream = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {

            try {

                if(inStream.available() != 0) {

                   // PacketHandler.handle();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
