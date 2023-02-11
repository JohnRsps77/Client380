package org.client.net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler implements Runnable {

    /**
     * amount of packet processes per client ticks
     */
    private static final int PACKET_PROCESSES_AMOUNT = 25;

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
                for(int i = 0; i < PACKET_PROCESSES_AMOUNT; i++) {
                    PacketHandler.handle(inStream);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
