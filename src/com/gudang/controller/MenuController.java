package com.gudang.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    void openBarang(ActionEvent event) {
        openNewWindow("/view/ManajemenBarang.fxml", "Manajemen Barang");
    }

    @FXML
    void openSupplier(ActionEvent event) {
        openNewWindow("/view/ManajemenSupplier.fxml", "Manajemen Supplier");
    }

    @FXML
    void openCustomer(ActionEvent event) {
        openNewWindow("/view/Customer.fxml", "Manajemen Customer");
    }

    private void openNewWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading: " + fxmlPath);
        }
    }
}
