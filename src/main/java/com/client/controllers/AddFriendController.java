package com.client.controllers;

import com.client.managers.SceneManager;
import com.client.model.SceneType;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFriendController implements Controller{
    @Override
    public void clear() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    SceneManager sceneManager = new SceneManager();
    @FXML
    private void backToMain() throws IOException
    {
        sceneManager.switchScene(SceneType.MAIN_SCENE);
    }
    @FXML
    private  void addFriend() throws  IOException
    {
        //Add friend function
        sceneManager.switchScene(SceneType.MAIN_SCENE);
    }
}
