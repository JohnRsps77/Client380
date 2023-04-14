package com.client;

import com.client.controllers.MainController;
import com.client.controllers.SignUpController;
import com.client.managers.SceneManager;
import com.client.model.*;
import com.client.managers.ImageManager;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    private RegistrationDetails registrationDetails;
    private final ImageManager imageManager = new ImageManager();
    private final SceneManager sceneManager = new SceneManager();
    private final HashMap<Long, GroupChat> groupChats = new HashMap<>();
    private GroupChat selectedGroupChat;
    private User user;

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
                ByteBuffer buffer = ByteBuffer.wrap(inputStream.readNBytes(inputStream.available()));
                switch (opcode) {
                    case 10:
                        readRegistrationResponse(buffer);
                        break;
                    case 11:
                        readGroupMessage(buffer);
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

    public void sendMessage(Message message) {
        byte[] messageBytes = message.text().getBytes();
        int length = messageBytes.length + 2;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put((byte) 3).put(messageBytes).put(STRING_TERMINATOR);
        try {
            outputStream.write(buffer.array());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readGroupMessage(ByteBuffer buffer) {
        byte[] bytes = new byte[buffer.remaining()];
        byte b;
        int i = 0;
        while((b = buffer.get()) != 0) {
            bytes[i] = b;
            i++;
        }
        String text = new String(Arrays.copyOfRange(bytes, 0, i), StandardCharsets.UTF_8);
        int k = i;
        while((b = buffer.get()) != 0) {
            bytes[i] = b;
            i++;
        }
        String name = new String(Arrays.copyOfRange(bytes, k, i), StandardCharsets.UTF_8);
        int j = i;
        while((b = buffer.get()) != 0) {
            bytes[i] = b;
            i++;
        }
        String imageLink = new String(Arrays.copyOfRange(bytes, j, i), StandardCharsets.UTF_8);
        long senderId = buffer.getLong();
        long groupId = buffer.getLong();
        Message message = new Message(senderId, name, text, 0, imageLink);
        GroupChat groupChat = groupChats.get(groupId);
        if(groupChat == null) return;
        groupChat.messageList().add(message);
        MainController controller = (MainController) sceneManager.getController(SceneType.MAIN_SCENE);
        Platform.runLater(() -> controller.constructMessage(message));
    }

    public void switchGroup(long id) {
        GroupChat groupChat = groupChats.get(id);
        if(groupChat == null) {
            return;
        }
        MainController controller = (MainController) sceneManager.getController(SceneType.MAIN_SCENE);
        controller.clear();
        List<Message> messageList = groupChat.messageList();
        for(Message m : messageList) {
            controller.constructMessage(m);
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
        this.registrationDetails = registrationDetails;
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

    public void readRegistrationResponse(ByteBuffer buffer) {
        byte response = buffer.get();
        if(response==0) {
            Platform.runLater(() -> {
                try {
                    sceneManager.switchScene(SceneType.COMPLETED_REGISTRATION_SCENE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return;
        }

        SignUpController controller = (SignUpController) sceneManager.getController(SceneType.SIGNUP_SCENE);
        controller.clear();
        controller.getErrorText().setText("Error when signing up or email is taken");
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

    public RegistrationDetails getRegistrationDetails() {
        return registrationDetails;
    }

    public HashMap<Long, GroupChat> getGroupChats() {
        return groupChats;
    }

    public GroupChat getSelectedGroupChat() {
        return selectedGroupChat;
    }

    public void setSelectedGroupChat(GroupChat selectedGroupChat) {
        this.selectedGroupChat = selectedGroupChat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
