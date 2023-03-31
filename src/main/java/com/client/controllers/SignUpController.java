package com.client.controllers;

import com.client.Client;
import com.client.managers.ImageManager;
import com.client.managers.SceneManager;
import com.client.model.RegistrationDetails;
import com.client.model.SceneType;
import com.sanctionco.jmail.JMail;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import okhttp3.Response;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.ResourceBundle;

public class SignUpController implements Controller {

    private static final String DEFAULT_IMAGE = "https://i.imgur.com/61psZF5.jpg";
    private String imageLink = DEFAULT_IMAGE;

    private final ImageManager imageManager = Client.getInstance().getImageManager();

    private final Validator<String> lengthValidator = Validator.createPredicateValidator(s -> s.length() >= 5 && s.length() <= 15, "Should be between 4 and 16 characters long.", Severity.ERROR);

    private final Validator<String> emailValidator = Validator.createPredicateValidator(JMail::isValid, "Please enter a valid email.");

    private final Validator<LocalDate> dateValidator = Validator.createPredicateValidator(dp ->
                    dp != null && dp.isBefore(ChronoLocalDate.from(ZonedDateTime.now().minusYears(12))),"You must be at least 12 years of age to sign up.");

    private ValidationSupport validationSupport;

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
        if(!validationSupport.isInvalid()) {
            String dateValue = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            RegistrationDetails registrationDetails = new RegistrationDetails(emailField.getText(), nameField.getText(), passwordField.getText(), dateValue, imageLink);
            Client.getInstance().sendRegistrationDetails(registrationDetails);
        } else {
            validationSupport.setErrorDecorationEnabled(true);
        }
    }

    public void addValidators() {
        validationSupport = new ValidationSupport();
        validationSupport.registerValidator(nameField, lengthValidator);
        validationSupport.registerValidator(emailField, emailValidator);
        validationSupport.registerValidator(passwordField, lengthValidator);
        validationSupport.registerValidator(datePicker, dateValidator);
        validationSupport.setErrorDecorationEnabled(false);
    }
    private final SceneManager sceneManager = Client.getInstance().getSceneManager();
    @FXML
    Button closeButton = new Button();
    @FXML
    Button minimizeButton = new Button();
    @FXML
    public void closeAction() throws IOException
    {
        sceneManager.closeWindow(closeButton);
    }
    @FXML
    public void minimizeAction() throws IOException
    {
        sceneManager.minimizeWindow(minimizeButton);
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

    @Override
    public void clear() {
        nameField.clear();
        passwordField.clear();
        emailField.clear();
        datePicker.getEditor().clear();
        imageLink = DEFAULT_IMAGE;
        imageView.setImage(imageManager.getFXImage(DEFAULT_IMAGE));
    }

    @FXML
    public void onCancelButton() throws IOException {
        Client.getInstance().getSceneManager().switchScene(SceneType.LOGIN_SCENE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::addValidators); //must call run later or reflection error occurs
    }
}
