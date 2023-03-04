package com.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public Scene loadScene(SceneType sceneType) {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource(sceneType.sceneFile));
        return null;
    }


    public enum SceneType {
        LOGIN_SCENE("views/login-view.fxml"),
        MAIN_SCENE("views/main-view.fxml");

        private final String sceneFile;

        SceneType(String sceneFile) {
            this.sceneFile = sceneFile;
        }
    }

    public void switchScene(ActionEvent event, SceneType sceneType) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(sceneType.sceneFile));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
