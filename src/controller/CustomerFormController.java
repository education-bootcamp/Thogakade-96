package controller;

import dao.DatabaseAccessCode;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.CustomerTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public Button btnSaveCustomer;
    public TableView<CustomerTm> tblCustomer;


    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        try{
            for (Customer c:new DatabaseAccessCode().loadAllCustomers()
             ) {
                Button btn= new Button("Delete");
                CustomerTm tm= new CustomerTm(
                        c.getId(),c.getName(),c.getAddress(),c.getSalary(),btn
                );
                obList.add(tm);
            }
            tblCustomer.setItems(obList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

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
