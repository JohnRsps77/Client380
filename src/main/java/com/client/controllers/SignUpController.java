package com.client.controllers;

import com.client.Client;
import com.client.model.RegistrationDetails;
import com.client.managers.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class SignUpController {

    private String imageLink = "https://i.imgur.com/61psZF5.jpg";

    private final ImageManager imageManager = Client.getInstance().getImageManager();

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
        Client.getInstance().sendRegistrationDetails(
                new RegistrationDetails(emailField.getText(), nameField.getText(), passwordField.getText(),
                        datePicker.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")), imageLink));
    }

    @FXML
    public void ChooseImageAction() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Choose Image...");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                new FileChooser.ExtensionFilter("JPG Files", "*.jpg")
        );

        File file = fileChooser.showOpenDialog(Client.getInstance().getPrimaryStage());

        if(file != null) {
            Path path = file.toPath();
            Image image = new Image(path.toUri().toString(), 128, 128, true, true);
            imageView.setImage(image);

            try(Response response = imageManager.uploadImage(Base64.getEncoder().encodeToString(Files.readAllBytes(path)))) {
                assert response.body() != null;
                imageLink = imageManager.getImageLink(response.body().string());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
