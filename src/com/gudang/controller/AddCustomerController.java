package com.gudang.controller;

import com.gudang.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCustomerController {

    @FXML private TextField txtIdCustomer;
    @FXML private TextField txtNamaCustomer;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelepon;
    @FXML private Button btnSimpan;

    private ManajemenCustomerController parentController;

    public void setParentController(ManajemenCustomerController controller) {
        this.parentController = controller;
    }

    @FXML
    public void initialize() {
        btnSimpan.setOnAction(e -> simpanCustomer());
    }

    private void simpanCustomer() {
        String id = txtIdCustomer.getText().trim();
        String nama = txtNamaCustomer.getText().trim();
        String email = txtEmail.getText().trim();
        String telepon = txtTelepon.getText().trim();

        if(id.isEmpty() || nama.isEmpty() || email.isEmpty()) {
            showAlert("Mandatory field harus diisi");
            return;
        }

        if(!email.contains("@")) {
            showAlert("Email tidak valid");
            return;
        }

        Customer customer = new Customer(id, nama, email, telepon);


        if(parentController != null) {
            parentController.addCustomer(customer);
        }

        Stage stage = (Stage) btnSimpan.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
