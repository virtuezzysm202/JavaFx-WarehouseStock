package com.gudang.controller;

import com.gudang.model.Customer;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManajemenCustomerController {

    @FXML
    private TableView<Customer> tableCustomer;

    @FXML
    private TableColumn<Customer, String> idColumnCustomer;

    @FXML
    private TableColumn<Customer, String> namaColumnCustomer;

    @FXML
    private TableColumn<Customer, String> emailColumnCustomer;

    @FXML
    private TableColumn<Customer, String> teleponColumnCustomer;

    @FXML
    private Button btnTambahCustomer;

    @FXML
    private Button btnEditCustomer;

    @FXML
    private Button btnHapusCustomer;

    private ObservableList<Customer> customerData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        idColumnCustomer.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getId()));
        namaColumnCustomer.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getNama()));
        emailColumnCustomer.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getEmail()));
        teleponColumnCustomer.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTelepon()));

        tableCustomer.setItems(customerData);

        btnTambahCustomer.setOnAction(e -> openAddCustomerWindow());
        btnEditCustomer.setOnAction(e -> openEditCustomerWindow());
        btnHapusCustomer.setOnAction(e -> hapusCustomer());
    }

    private void openAddCustomerWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddCustomer.fxml"));
            Parent root = loader.load();

            AddCustomerController addController = loader.getController();
            addController.setParentController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Customer");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error membuka form Add Customer!");
        }
    }

    private void openEditCustomerWindow() {
        Customer selected = tableCustomer.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Pilih Customer yang ingin diedit!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditCustomer.fxml"));
            Parent root = loader.load();

            EditCustomerController controller = loader.getController();
            controller.setCustomerData(selected, this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Customer");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Gagal membuka form Edit Customer!");
        }
    }


    private void hapusCustomer() {
        Customer selected = tableCustomer.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Pilih customer yang ingin dihapus!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText(null);
        alert.setContentText("Apakah Anda yakin ingin menghapus data customer ini?");

        ButtonType yesButton = new ButtonType("Ya", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Tidak", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                customerData.remove(selected);
            }

        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void addCustomer(Customer customer) {
        for (Customer c : customerData) {
            if (c.getId().equals(customer.getId())) {
                showAlert("ID customer sudah ada");
                return;
            }
        }
        customerData.add(customer);
    }

    public void refreshTable() {
        tableCustomer.refresh();
    }
}
