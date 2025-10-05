package com.gudang.controller;

import com.gudang.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCustomerController {

    @FXML private TextField txtIdCustomer;
    @FXML private TextField txtNamaCustomer;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelepon;
    @FXML private Button btnUpdate;

    private Customer currentCustomer;
    private ManajemenCustomerController parentController;

    public void setCustomerData(Customer customer, ManajemenCustomerController controller) {
        this.currentCustomer = customer;
        this.parentController = controller;

        // Pre-fill field
        txtIdCustomer.setText(customer.getId());
        txtNamaCustomer.setText(customer.getNama());
        txtEmail.setText(customer.getEmail());
        txtTelepon.setText(customer.getTelepon());
    }

    @FXML
    public void initialize() {
        btnUpdate.setOnAction(e -> handleUpdate());
    }

    private void handleUpdate() {
        String nama = txtNamaCustomer.getText().trim();
        String email = txtEmail.getText().trim();
        String telepon = txtTelepon.getText().trim();

        // Validasi
        if (nama.isEmpty() || email.isEmpty()) {
            showAlert("Mandatory field harus diisi");
            return;
        }
        if (!email.contains("@")) {
            showAlert("Email tidak valid");
            return;
        }

        currentCustomer.setNama(nama);
        currentCustomer.setEmail(email);
        currentCustomer.setTelepon(telepon);

        parentController.refreshTable();

        //Close window
        Stage stage = (Stage) btnUpdate.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
