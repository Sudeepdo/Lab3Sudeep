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
import java.sql.*;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    public TextField iemployee_id;
    public TextField iemployee_name;
    public TextField iaddress;
    public TextField isalary;
    public Label msg;

    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, Integer> employee_id;
    @FXML
    private TableColumn<Employee, String> employee_name;
    @FXML
    private TableColumn<Employee, String> address;
    @FXML
    private TableColumn<Employee, String> salary;
    ObservableList<Employee> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employee_id.setCellValueFactory(new
                PropertyValueFactory<Employee, Integer>("employee_id"));
        employee_name.setCellValueFactory(new
                PropertyValueFactory<Employee, String>("employee_name"));
        address.setCellValueFactory(new
                PropertyValueFactory<Employee, String>("address"));
        salary.setCellValueFactory(new
                PropertyValueFactory<Employee, String>("salary"));
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
            String query = "SELECT * FROM employee";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            list.clear();
            tableView.setItems(list);
            while (resultSet.next()) {
                int employee_id = resultSet.getInt("employee_id");
                String employee_name = resultSet.getString("employee_name");
                String address = resultSet.getString("address");
                String salary = resultSet.getString("salary");
                tableView.getItems().add(new Employee(employee_id, employee_name, address,
                        salary));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadData(ActionEvent actionEvent) {

        String getid = iemployee_id.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM employee WHERE `employee_id` = '" + getid + "' ";

            msg.setText(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int employee_id = resultSet.getInt("employee_id");
                String employee_name = resultSet.getString("employee_name");
                String address = resultSet.getString("address");
                String salary = resultSet.getString("salary");
                iemployee_name.setText(employee_name);
                iaddress.setText(address);
                isalary.setText(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void DeleteData(ActionEvent actionEvent) {
        String id = iemployee_id.getText(); // Get the ID from the input field

        String jdbcUrl = "jdbc:mysql://localhost:3306/management";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Set getid to the value of id to use it in the query
            String getid = id;
            String query = "DELETE FROM employee WHERE `employee_id`= '" + getid + "' ";

            Statement statement = connection.createStatement();
            statement.execute(query);

            // Refresh the table data after deletion
            onHelloButtonClick();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void InsertData(ActionEvent actionEvent) {
        String getemployee_id = iemployee_id.getText();
        String getemployee_name = iemployee_name.getText();
        String getaddress = iaddress.getText();
        Double getsalary = Double.valueOf(isalary.getText());

        String Fsalary = String.valueOf(calculatesalary(getsalary));


        String jdbcUrl = "jdbc:mysql://localhost:3306/management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `employee`(`employee_id`,`employee_name`, `address`, `salary`) VALUES('" + getemployee_id + "', '"  + getemployee_name + "', '" + getaddress+ "', '" + Fsalary+ "')";

            msg.setText(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void UpdateData(ActionEvent actionEvent) {
        String getemployee_id = iemployee_id.getText();
        String getemployee_name = iemployee_name.getText();
        String getaddress = iaddress.getText();

        Double getsalary = Double.valueOf(isalary.getText());

        String Fsalary = String.valueOf(calculatesalary(getsalary));


        String jdbcUrl = "jdbc:mysql://localhost:3306/management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "UPDATE `Employee` SET `employee_name`='"+getemployee_name+"',`address`='"+getaddress+"',`salary`='"+getsalary+"' WHERE `employee_id` = '"+getemployee_id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the databa

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
                Stage firstSceneStage = (Stage) iemployee_id.getScene().getWindow();
                firstSceneStage.close();


                secondStage.show();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        public Double calculatesalary(Double salary){
        Double yearly = 12*salary;
        return yearly;
        }
    }

