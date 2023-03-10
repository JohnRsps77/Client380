package com.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationLauncher extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        new Client(stage).start();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Application");
        stage.setScene(scene);
        stage.show();

        Client.getInstance().getSceneManager().cachedScenes.put(SceneManager.SceneType.LOGIN_SCENE, root);
    }

    public static void main(String[] args) {
        launch();
    }
}