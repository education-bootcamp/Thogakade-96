package controller;

import dao.DatabaseAccessCode;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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
    public AnchorPane customerContext;

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


        loadAllCustomers();


        //==========listeners
        tblCustomer.getSelectionModel()
                .selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
            setData(newValue);
        }));

    }

    private void setData(CustomerTm tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtSalary.setText(String.valueOf(tm.getSalary()));
        btnSaveCustomer.setText("Update Customer");
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



                    btn.setOnAction(e->{
                        try{
                        Alert alert= new Alert(Alert.AlertType.CONFIRMATION,
                                "Are you sure?", ButtonType.YES, ButtonType.NO);
                        Optional<ButtonType> buttonType = alert.showAndWait();
                        if (buttonType.get()==ButtonType.YES){
                            if (new DatabaseAccessCode().deleteCustomer(tm.getId())){
                                new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted!").show();
                                loadAllCustomers();
                            }else{
                                new Alert(Alert.AlertType.WARNING,"Try Again").show();
                            }
                        }

                        }catch (Exception exception){
                            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
                            exception.printStackTrace();
                        }
                    });


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

        if (btnSaveCustomer.getText().equalsIgnoreCase("Save Customer")){
            try {
                boolean isCustomerSaved = new DatabaseAccessCode().saveCustomer(c1);
                if (isCustomerSaved){
                    loadAllCustomers();
                    clear();
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();
                }
            }catch (SQLException | ClassNotFoundException e){
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
                e.printStackTrace();
            }
        }else{
            try {
                boolean isCustomerUpdated = new DatabaseAccessCode().updateCustomer(c1);
                if (isCustomerUpdated){
                    loadAllCustomers();
                    clear();
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();
                }
            }catch (SQLException | ClassNotFoundException e){
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
                e.printStackTrace();
            }
        }
    }
    private void clear(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
        clear();
        btnSaveCustomer.setText("Save Customer");
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) customerContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(
                getClass().getResource("../view/MainForm.fxml"))));
        stage.centerOnScreen();
    }
}
