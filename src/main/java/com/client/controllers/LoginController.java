package com.client.controllers;

import com.client.Client;
import com.client.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public void switchToMainScene() throws IOException {
        Client.getInstance().getSceneManager().switchScene(SceneManager.SceneType.MAIN_SCENE);
    }

    @FXML
    public void switchToSignUpScene() throws IOException {
        Client.getInstance().getSceneManager().switchScene(SceneManager.SceneType.SIGNUP_SCENE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void moreHelp(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }
}