package com.example.lab3f;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    public TextField username;
    public PasswordField password;
    public Label errorMessage;


    @FXML


    public void login(ActionEvent actionEvent) {

        String uname = username.getText();
        String upassword = password.getText();

        // Check for empty username or password with OR (||)
        if (uname.equals("") || upassword.equals("")) {
            errorMessage.setText("Please enter username and password");
        } else {
            String jdbcUrl = "jdbc:mysql://localhost:3306/management";
            String dbUser = "root";
            String dbPassword = "";

            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                // Correct SQL query syntax by removing single quotes around column names
                String query = "SELECT * FROM admin WHERE email='" + uname + "' AND password='" + upassword + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                // Clear and set the table view

                // Check if login was successful
                if (resultSet.next()) {
                    // Load the dashboard only if login is successful
                    try {
                        Parent secondScene = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                        Stage secondStage = new Stage();
                        secondStage.setTitle("Dashboard");
                        secondStage.setScene(new Scene(secondScene));
                        Stage firstSceneStage = (Stage) username.getScene().getWindow();
                        firstSceneStage.close();

                        secondStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Display error message for incorrect credentials
                    errorMessage.setText("Invalid username / password");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


