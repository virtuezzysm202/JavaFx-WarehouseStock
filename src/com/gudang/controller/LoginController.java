package com.gudang.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;  //
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUser;  //

    @FXML
    private PasswordField txtPass;

    @FXML
    private Label lblStatus;

    @FXML
    void loginAction(ActionEvent event) {
        String username = txtUser.getText();
        String password = txtPass.getText();


        if(username.isEmpty() || password.isEmpty()) {
            lblStatus.setText("Username dan Password harus diisi!");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }


        if(username.equals("admin") && password.equals("123")) {
            lblStatus.setText("Login Berhasil!");
            lblStatus.setStyle("-fx-text-fill: green;");


            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Menu Utama");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                lblStatus.setText("Error: Tidak dapat membuka Menu!");
                lblStatus.setStyle("-fx-text-fill: red;");
            }

        } else {
            lblStatus.setText("Login Gagal! Username atau Password salah");
            lblStatus.setStyle("-fx-text-fill: red;");
        }
    }
}