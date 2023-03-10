package com.client;

import com.client.managers.SceneManager;
import com.client.model.LoginDetails;
import com.client.model.RegistrationDetails;
import com.client.managers.ImageManager;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements Runnable {

    private static Client INSTANCE;
    private static final int PORT = 85;
    private static final String host = "localhost";
    private static final byte STRING_TERMINATOR = 0;

    public InputStream inputStream;
    public OutputStream outputStream;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final Stage primaryStage;

    private final ImageManager imageManager = new ImageManager();
    private final SceneManager sceneManager = new SceneManager();

    public Client(Stage primaryStage) {
        this.primaryStage = primaryStage;
        INSTANCE = this;
    }

    public void start() {
        executorService.submit(this);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void eventLoop() throws InterruptedException {
        while(true) {
            try {
                int opcode = inputStream.read();

                switch (opcode) {
                    case 10:

                        break;
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
        String email = registrationDetails.email();
        String username = registrationDetails.username();
        String password = registrationDetails.password();
        String DOB = registrationDetails.DOB();
        String imageLink = registrationDetails.imageLink();

        int size = email.length() + username.length() + password.length() + DOB.length() + imageLink.length() + 6;
        ByteBuffer buffer = ByteBuffer.allocate(size);

        buffer.put((byte) 0)
                .put(email.getBytes())
                .put(STRING_TERMINATOR)
                .put(username.getBytes())
                .put(STRING_TERMINATOR)
                .put(password.getBytes())
                .put(STRING_TERMINATOR)
                .put(DOB.getBytes())
                .put(STRING_TERMINATOR)
                .put(imageLink.getBytes())
                .put(STRING_TERMINATOR);
        try {
            outputStream.write(buffer.array());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public ImageManager getImageManager() {
        return imageManager;
    }

    public static Client getInstance() {
        return INSTANCE;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


}
