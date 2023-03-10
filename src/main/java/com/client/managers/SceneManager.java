package com.client.managers;

import com.client.Client;
import com.client.model.SceneType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class SceneManager {

    public HashMap<SceneType, Parent> cachedScenes = new HashMap<>();

    public void switchScene(SceneType sceneType) throws IOException {
        Parent parent;
        Stage stage = Client.getInstance().getPrimaryStage();
        Scene scene = stage.getScene();

        if(cachedScenes.containsKey(sceneType)) {
            parent = cachedScenes.get(sceneType);
            scene.setRoot(parent);
        } else {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(sceneType.getPath())));
            cachedScenes.put(sceneType, parent);
            scene.setRoot(parent);
        }

        stage.show();
    }
}
