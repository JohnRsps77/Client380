package com.client.controllers;

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



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.image.BufferedImage;

public class SignUpController {

    String name, passWord, email, dob;
    LocalDate dateOfBirth;
    @FXML
    private TextField txt_name;
    @FXML
    private PasswordField txt_Password;
    @FXML
    private TextField txt_Email;
    @FXML
    private DatePicker txt_DoB;
    @FXML
    private ImageView img_profileImg;
    private FileChooser fileChoser;
    private File filePath;
    @FXML
    public void SignUpAction(ActionEvent event) throws IOException
    {
        name = txt_name.getText();
        passWord = txt_Password.getText();
        email = txt_Email.getText();
        dateOfBirth = txt_DoB.getValue();
        dob = dateOfBirth.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        System.out.println(name);
    }
    @FXML
    public void ChooseImageAction(ActionEvent event) throws IOException
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        fileChoser = new FileChooser();
        fileChoser.setTitle("Chose Image...");
        fileChoser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png")
                ,new FileChooser.ExtensionFilter("JPEG Files", "*.jpeg")
        );
        this.filePath = fileChoser.showOpenDialog(stage);
        try {
            Image image = new Image(new FileInputStream(filePath),128,128,false,false);
            img_profileImg.setImage(image);
        }catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
