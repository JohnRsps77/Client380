package com.client.managers;

import com.client.Client;
import com.client.controllers.Controller;
import com.client.model.SceneType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class SceneManager {

    public HashMap<SceneType, SceneController> cache = new HashMap<>();

    public void switchScene(SceneType sceneType) throws IOException {
        Stage stage = Client.getInstance().getPrimaryStage();

        if(cache.containsKey(sceneType)) {
            SceneController sceneController = cache.get(sceneType);
            sceneController.controller().clear();
            stage.setScene(sceneController.scene);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource(sceneType.getPath())));//(Objects.requireNonNull(getClass().getClassLoader().getResource(sceneType.getPath()))));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            cache.put(sceneType, new SceneController(scene, fxmlLoader.getController()));
            stage.setScene(scene);
        }

        stage.setWidth(sceneType.getWidth());
        stage.setHeight(sceneType.getHeight());
    }

    public void closeWindow(Button closeButton) throws IOException{
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void minimizeWindow(Button closeButton) throws IOException{
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    public record SceneController(Scene scene, Controller controller){}
}
