package com.client.controllers;

import com.client.Client;
import com.client.model.RegistrationDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    String name, passWord, email, dob;
    @FXML
    private TextField txt_name;
    @FXML
    private PasswordField txt_Password;
    @FXML
    private TextField txt_Email;
    @FXML
    private DatePicker txt_DoB;

    @FXML
    public void SignUpAction(ActionEvent event) {
        name = txt_name.getText();
        passWord = txt_Password.getText();
        email = txt_Email.getText();
        dob = txt_DoB.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        Client.INSTANCE.sendRegistrationDetails(
                new RegistrationDetails(email, name, passWord, dob, 4, new byte[] {3,53,32,42}));

        System.out.println(name);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
