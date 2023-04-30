package com.client.controllers;

import com.client.Client;
import com.client.managers.ImageManager;
import com.client.managers.SceneManager;
import com.client.model.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.ResourceBundle;

public class CreateGroupController implements Controller{
    @Override
    public void clear() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private ImageView imageView;
    @FXML
    private TextField txt_GroupName;

    SceneManager sceneManager = new SceneManager();

    private String groupName;

    private final ImageManager imageManager = Client.getInstance().getImageManager();

    private static final String DEFAULT_IMAGE = "https://i.imgur.com/61psZF5.jpg";
    private String imageLink = DEFAULT_IMAGE;
    @FXML
    private void BackAction() throws IOException {
        sceneManager.switchScene(SceneType.MAIN_SCENE);
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

        if (file != null) {
            Path path = file.toPath();
            Image image = new Image(path.toUri().toString(), 128, 128, true, true);
            imageView.setImage(image);

            try (Response response = imageManager.uploadImage(Base64.getEncoder().encodeToString(Files.readAllBytes(path)))) {
                assert response.body() != null;
                imageLink = imageManager.getImageLink(response.body().string());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void createGroupAction()
    {
        groupName = txt_GroupName.getText();
    }
}
