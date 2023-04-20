package com.client.controllers;

import com.client.Client;
import com.client.managers.SceneManager;
import com.client.model.Message;
import com.client.model.SceneType;
import com.client.model.User;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Controller {

    private final SceneManager sceneManager = Client.getInstance().getSceneManager();

    @FXML
    private VBox mainVbox;

    @FXML
    private VBox friendVbox;
    @FXML
    private TextField textField;

    @FXML
    private ScrollPane chatScrollPane;

    @FXML
    private Button closeButton = new Button();

    @FXML
    private Button minimizeButton = new Button();

    private double x = 0;
    private double y = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        chatScrollPane.vvalueProperty().bind(mainVbox.heightProperty());
        mainVbox.setSpacing(15);
        friendVbox.setSpacing(10);
        friendVbox.setPadding(new Insets(10));
        createFriendList(1);
        createFriendList(1);
    }

    public void constructMessage(Message message)
    {
        Image image = Client.getInstance().getImageManager().getFXImage(message.imageLink());
        ImageView profileImgView = new ImageView(image);
        Label username = new Label(message.userName());
        Label text = new Label(message.text());
        Label timestamp = new Label("" + message.timestamp());

        profileImgView.setPreserveRatio(true);
        profileImgView.setFitHeight(40);

        VBox vboxProfile = new VBox();
        VBox vboxMessage = new VBox();
        HBox hbox = new HBox();

        vboxProfile.setAlignment(Pos.CENTER);
        vboxMessage.setSpacing(5);
        vboxProfile.setSpacing(5);
        hbox.setSpacing(5);

        hbox.getChildren().add(vboxProfile);
        hbox.getChildren().add(vboxMessage);

        vboxProfile.getChildren().add(profileImgView);
        vboxProfile.getChildren().add(username);

        vboxMessage.getChildren().add(text);
        vboxMessage.getChildren().add(timestamp);

        mainVbox.getChildren().add(hbox);
    }

    public void createFriendList(int userID)
    {
        String friendImgLink = "images/profile.png";
        String friendName = "Friend's Name";
        HBox friendHBox = new HBox();
        Image friendImg = new Image(friendImgLink);
        ImageView friendImgView = new ImageView(friendImg);
        friendImgView.setPreserveRatio(true);
        friendImgView.setFitHeight(40);

        Label lbl_Friend = new Label(friendName);
        friendHBox.setSpacing(5);
        lbl_Friend.setPadding(new Insets(10));



        friendHBox.getChildren().add(friendImgView);
        friendHBox.getChildren().add(lbl_Friend);

        friendHBox.setStyle("-fx-background-color: rgb(102, 205, 170);");
        friendVbox.getChildren().add(friendHBox);

        friendHBox.addEventFilter(MouseEvent.MOUSE_ENTERED, even ->{
            friendHBox.setStyle("-fx-background-color: rgb(32,178,170);");
        });
        friendHBox.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
            friendHBox.setStyle("-fx-background-color: rgb(102, 205, 170);");
        });
        friendHBox.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            getChatFriend();
        });

    }

    private void getChatFriend()
    {

    }
    @FXML
    private void sendMessageByEnter(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
        {
            if(textField.getText().isEmpty()) return;
            Client client = Client.getInstance();
            User user = client.getUser();
            Client.getInstance().sendMessage(new Message(user.getUuid(), user.getUsername(), textField.getText(), System.currentTimeMillis(), user.getProfileImageLink()));
            textField.clear();
        }
    }

    @FXML
    private void createGroupPopUp () throws IOException {
        sceneManager.switchScene(SceneType.CREATE_GROUP_POPUP_SCENE);
    }

    @FXML
    private void addFriendPopUp() throws  IOException{

    }
    @FXML
    public void closeAction() throws IOException
    {
        sceneManager.closeWindow(closeButton);
    }

    @FXML
    public void minimizeAction() throws IOException
    {
        sceneManager.minimizeWindow(minimizeButton);
    }

    @FXML
    public void menuPane_dragged(MouseEvent event)
    {
        Stage stage = Client.getInstance().getPrimaryStage();
        stage.setX(event.getScreenX() + x);
        stage.setY(event.getScreenY() + y);
    }

    @FXML
    public void menuPane_pressed(MouseEvent event)
    {
        Stage stage = Client.getInstance().getPrimaryStage();
        x = stage.getX() - event.getScreenX();
        y = stage.getY() - event.getScreenY();
    }

    @Override
    public void clear() {
        mainVbox.getChildren().clear();
    }
}
