package com.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public void SignUpAction(ActionEvent event) throws IOException
    {
        name = txt_name.getText();
        passWord = txt_Password.getText();
        email = txt_Email.getText();
        dateOfBirth = txt_DoB.getValue();
        dob = dateOfBirth.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        System.out.println(name);
    }

}
