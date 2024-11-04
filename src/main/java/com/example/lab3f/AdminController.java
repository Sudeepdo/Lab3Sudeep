package com.example.lab3f;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
public class AdminController implements Initializable {
    public TextField iadmin_id;
    public TextField iadmin_name;
    public TextField iemail;
    public TextField ipassword;
    public Label msg;

    @FXML
    private TableView<Admin> tableView;
    @FXML
    private TableColumn<Admin, Integer> admin_id;
    @FXML
    private TableColumn<Admin, String> admin_name;
    @FXML
    private TableColumn<Admin, String> email;
    @FXML
    private TableColumn<Admin, String> password;
    ObservableList<Admin> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin_id.setCellValueFactory(new
                PropertyValueFactory<Admin, Integer>("admin_id"));
        admin_name.setCellValueFactory(new
                PropertyValueFactory<Admin, String>("admin_name"));
        email.setCellValueFactory(new
                PropertyValueFactory<Admin, String>("email"));
        password.setCellValueFactory(new
                PropertyValueFactory<Admin, String>("password"));
        tableView.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }

    public void populateTable() {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM admin";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            list.clear();
            tableView.setItems(list);
            while (resultSet.next()) {
                int admin_id = resultSet.getInt("admin_id");
                String admin_name = resultSet.getString("admin_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                tableView.getItems().add(new Admin(admin_id, admin_name, email,
                        password));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadData(ActionEvent actionEvent) {

        String getid = iadmin_id.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM admin WHERE `admin_id` = '" + getid + "' ";

            msg.setText(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int admin_id = resultSet.getInt("admin_id");
                String admin_name = resultSet.getString("admin_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                iadmin_name.setText(admin_name);
                iemail.setText(email);
                ipassword.setText(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void DeleteData(ActionEvent actionEvent) {
        String getid = iadmin_id.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM admin WHERE `admin_id`= '" + getid + "' ";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database
            onHelloButtonClick();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertData(ActionEvent actionEvent) {
        String getadmin_id = iadmin_id.getText();
        String getadmin_name = iadmin_name.getText();
        String getemail = iemail.getText();
        String getpassword = ipassword.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `admin`(`admin_id`,`admin_name`, `email`, `password`) VALUES('" + getadmin_id + "', '"  + getadmin_name + "', '" + getemail+ "', '" + getpassword+ "')";

            msg.setText(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void UpdateData(ActionEvent actionEvent) {
        String getadmin_id = iadmin_id.getText();
        String getadmin_name = iadmin_name.getText();
        String getemail = iemail.getText();
        String getpassword = ipassword.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "UPDATE `admin` SET `admin_name`='"+getadmin_name+"',`email`='"+getemail+"',`password`='"+getpassword+"' WHERE `admin_id` = '"+getadmin_id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database
            onHelloButtonClick();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void backClick(ActionEvent actionEvent) {

        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("Employee");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) iadmin_id.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

