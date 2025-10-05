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

    private ObservableList<Customer> customerData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        idColumnCustomer.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getId()));
        namaColumnCustomer.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getNama()));
        emailColumnCustomer.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getEmail()));
        teleponColumnCustomer.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTelepon()));

        tableCustomer.setItems(customerData);

        btnTambahCustomer.setOnAction(e -> openAddCustomerWindow());
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
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void addCustomer(Customer customer) {
        for(Customer c : customerData) {
            if(c.getId().equals(customer.getId())) {
                showAlert("ID customer sudah ada");
                return;
            }
        }
        customerData.add(customer);
    }
}
