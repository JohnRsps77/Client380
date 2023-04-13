package com.client.controllers;

import com.client.Client;
import com.client.managers.SceneManager;
import javafx.fxml.FXML;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Controller {
    @Override
    public void clear() {

    }
    @FXML
    VBox mainVbox;
    @FXML
    TextField txt_Chat;
    @FXML
    ScrollPane chatScrollPane;

    record Message(int userID, String userName, String messageText, String time, String imageLink){}

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        chatScrollPane.vvalueProperty().bind(mainVbox.heightProperty());
        mainVbox.setSpacing(15);
        Message message = new Message(1,"UserName", "This is a message", "0:00", "images/defaultImage.png");
        createChat(message);
    }

    private void createChat(Message message)
    {
        ImageView profileImgView = new ImageView(new Image(message.imageLink));
        Label lbl_UserName = new Label(message.userName);
        Label lbl_Message = new Label(message.messageText);
        Label lbl_Time = new Label(message.time);

        profileImgView.setPreserveRatio(true);
        profileImgView.setFitHeight(40);

        VBox vBox_profile = new VBox();
        VBox vBox_message = new VBox();
        HBox hBox_chat = new HBox();


        vBox_profile.setAlignment(Pos.CENTER);
        vBox_message.setSpacing(5);
        vBox_profile.setSpacing(5);

        hBox_chat.setSpacing(5);


        hBox_chat.getChildren().add(vBox_profile);
        hBox_chat.getChildren().add(vBox_message);

        vBox_profile.getChildren().add(profileImgView);
        vBox_profile.getChildren().add(lbl_UserName);

        vBox_message.getChildren().add(lbl_Message);
        vBox_message.getChildren().add(lbl_Time);

        mainVbox.getChildren().add(hBox_chat);
    }

    @FXML
    private void sendMessageByEnter(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
        {
            Message message = new Message(1, "Usernam", txt_Chat.getText(), "0:00", "images/defaultImage.png");
            createChat(message);
            txt_Chat.clear();
        }
    }


    private final SceneManager sceneManager = Client.getInstance().getSceneManager();
    @FXML
    Button closeButton = new Button();
    @FXML
    Button minimizeButton = new Button();
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

    private double x = 0;
    private double y = 0;

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
}
