package com.client.controllers;

import com.client.Client;
import com.client.model.RegistrationDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class SignUpController {

    private String name, password, email, dob;
    private byte[] imageBytes = new byte[0];

    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ImageView imageView;

    @FXML
    public void SignUpAction() {
        name = nameField.getText();
        password = passwordField.getText();
        email = emailField.getText();
        dob = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        Client.getInstance().sendRegistrationDetails(
                new RegistrationDetails(email, name, password, dob, imageBytes.length, imageBytes));
    }

    @FXML
    public void ChooseImageAction() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Chose Image...");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png")
        );

        File file = fileChooser.showOpenDialog(Client.getInstance().primaryStage);

        if(file != null) {
            try {
                imageBytes = Files.readAllBytes(Path.of(file.getPath()));
                Image image = new Image(new FileInputStream(file), 128, 128, false, false);
                imageView.setImage(image);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
