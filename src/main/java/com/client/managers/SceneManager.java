package com.client.managers;

import com.client.Client;
import com.client.controllers.Controller;
import com.client.model.SceneType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
            putCache(sceneType, new SceneController(scene, fxmlLoader.getController()));
            stage.setScene(scene);
        }

        stage.setWidth(sceneType.getWidth());
        stage.setHeight(sceneType.getHeight());

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth()-stage.getWidth())/2);
        stage.setY((screenBounds.getHeight()-stage.getHeight())/2);
    }

    public Controller getController(SceneType sceneType) {
        return cache.get(sceneType).controller;
    }

    public void closeWindow(Button closeButton) throws IOException{
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void minimizeWindow(Button closeButton) throws IOException{
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    public void putCache(SceneType sceneType, SceneController controller) {
        cache.put(sceneType, controller);
    }

    public record SceneController(Scene scene, Controller controller){}
}
