package com.client.controllers;

import com.client.Client;
import com.client.model.RegistrationDetails;
import com.client.model.SceneType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CompletedRegistrationController implements Controller {

    private Client client = Client.getInstance();

    @FXML
    private ImageView completedRegistrationImage;

    @FXML
    private Text welcomeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegistrationDetails registrationDetails = client.getRegistrationDetails();
        welcomeText.setText("Welcome " + registrationDetails.username() + "!");
        completedRegistrationImage.setImage(client.getImageManager().getFXImage(registrationDetails.imageLink()));
    }

    @FXML
    public void signInFromRegistration() throws IOException {
        client.getSceneManager().switchScene(SceneType.LOGIN_SCENE);
    }

    @Override
    public void clear() {

    }
}
