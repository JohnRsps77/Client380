package com.client.controllers;

import com.client.Client;
import com.client.managers.SceneManager;
import com.client.model.SceneType;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Controller {

    private final SceneManager sceneManager = Client.getInstance().getSceneManager();

    @FXML
    public void switchToMainScene() throws IOException {
        sceneManager.switchScene(SceneType.MAIN_SCENE);
    }

    @FXML
    public void switchToSignUpScene() throws IOException {
        sceneManager.switchScene(SceneType.SIGNUP_SCENE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void moreHelp(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }

    @Override
    public void clear() {

    }
}