package com.client.controllers;

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

    public final SceneManager sceneManager = new SceneManager();

    @FXML
    public void switchToMainScene(ActionEvent event) throws IOException {
        SceneManager.SceneType sceneType = SceneManager.SceneType.MAIN_SCENE;
        sceneManager.switchScene(event, sceneType);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    public void moreHelp(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }
}