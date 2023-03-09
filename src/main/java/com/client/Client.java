package com.client;

import com.client.model.LoginDetails;
import com.client.model.RegistrationDetails;

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
    private static final byte STRING_TERMINATOR = 0;
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
                //@todo add log file
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

    public void sendMessage(String message) {
        byte[] messageBytes = message.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(messageBytes.length + 1);
        buffer.put(messageBytes).put(STRING_TERMINATOR);
        try {
            outputStream.write(buffer.array());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendButtonClick(int btnId) {
        try {
            outputStream.write(btnId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendLoginDetails(LoginDetails loginDetails) {

    }

    public void sendRegistrationDetails(RegistrationDetails registrationDetails) {

    }
}
