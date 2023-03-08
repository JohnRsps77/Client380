package com.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements Runnable {

    public static Client INSTANCE = new Client();
    private static final int PORT = 85;
    private static final String host = "localhost";

    public InputStream inputStream;
    public OutputStream outputStream;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void start() {
        executorService.submit(this);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void eventLoop() throws InterruptedException {
        while(true) {
            try {
                int opcode = inputStream.read();

                switch (opcode) {

                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    @Override
    public void run() {
        try(Socket socket = new Socket(host, PORT)) {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            // start event loop
            eventLoop();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
