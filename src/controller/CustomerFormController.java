package controller;

import dao.DatabaseAccessCode;
import entity.Customer;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CustomerFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public Button btnSaveCustomer;

    public void saveCustomerOnAction(ActionEvent actionEvent) {
        Customer c1= new Customer(
                txtId.getText(),txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText())
        );
        try {
            boolean isCustomerSaved = new DatabaseAccessCode().saveCustomer(c1);
            if (isCustomerSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            e.printStackTrace();
        }
    }
}
