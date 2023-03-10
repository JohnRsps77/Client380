package com.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class SceneManager {

    public HashMap<SceneType, Parent> cachedScenes = new HashMap<>();

    public enum SceneType {
        LOGIN_SCENE("views/login-view.fxml"),
        MAIN_SCENE("views/main-view.fxml"),
        SIGNUP_SCENE("views/signup-view.fxml");

        private final String sceneFile;
        SceneType(String sceneFile) {
            this.sceneFile = sceneFile;
        }
    }

    public void switchScene(SceneType sceneType) throws IOException {
        Parent parent;
        if(cachedScenes.containsKey(sceneType)) {
            parent = cachedScenes.get(sceneType);
            Client.getInstance().primaryStage.getScene().setRoot(parent);
        } else {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(sceneType.sceneFile)));
            cachedScenes.put(sceneType, parent);
            Client.getInstance().primaryStage.getScene().setRoot(parent);
        }

        Client.getInstance().primaryStage.show();
    }

}
