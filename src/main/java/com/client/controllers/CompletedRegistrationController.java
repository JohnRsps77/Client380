package com.client.controllers;

import com.client.Client;
import com.client.model.RegistrationDetails;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CompletedRegistrationController implements Initializable {

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
    public void signInFromRegistration() {

    }
}
