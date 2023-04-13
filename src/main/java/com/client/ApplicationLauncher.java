package com.client;

import com.client.managers.SceneManager;
import com.client.model.SceneType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ApplicationLauncher extends Application {

    private static final String TITLE = "Application";

    @Override
    public void start(Stage stage) throws IOException {
        new Client(stage).start();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(SceneType.MAIN_SCENE.getPath()));
        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
        Client.getInstance().getSceneManager().cache.put(SceneType.LOGIN_SCENE, new SceneManager.SceneController(scene, fxmlLoader.getController()));
    }

    public static void main(String[] args) {
        launch();
    }
}