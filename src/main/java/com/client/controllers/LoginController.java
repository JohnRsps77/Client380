package com.client.controllers;

import com.client.Client;
import com.client.managers.SceneManager;
import com.client.model.LoginDetails;
import com.client.model.Message;
import com.client.model.SceneType;
import com.client.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Controller {

    private final SceneManager sceneManager = Client.getInstance().getSceneManager();

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button closeButton = new Button();
    @FXML
    private Button minimizeButton = new Button();

    @FXML
    public void onLoginClicked() {
        if(username.getText().length() > 0 && password.getText().length() > 0) {
            Client.getInstance().sendLoginDetails(new LoginDetails(username.getText(), password.getText()));
        }
      sceneManager.switchScene(SceneType.MAIN_SCENE);
    }

    @FXML
    public void switchToSignUpScene() {
        sceneManager.switchScene(SceneType.SIGNUP_SCENE);
    }

    @FXML
    public void switchToOptionSceneTest() {
        sceneManager.switchScene(SceneType.OPTION_SCENE);
    }

    @FXML
    private void sendMessageByEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
        {
            onLoginClicked();
        }
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void moreHelp(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }



    @Override
    public void clear()
    {

    }
}