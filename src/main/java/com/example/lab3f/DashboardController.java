package com.example.lab3f;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class DashboardController {

    public Button adminbuttom;

    public void adminClick(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("admin.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("Dashboard");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) adminbuttom.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void admin(ActionEvent actionEvent) {
    }

    public void employeeClick(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("employee.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("Employee");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) adminbuttom.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    public void exitClick(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void logoutClick(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("Login");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) adminbuttom.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

