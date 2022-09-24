package controller;

import dao.DatabaseAccessCode;
import entity.Item;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

public class ItemFormController {
    public AnchorPane itemFormContext;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public Button btnSaveItem;
    public TableView tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colOption;

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }

    public void newItemOnAction(ActionEvent actionEvent) {
    }

    public void saveItemOnAction(ActionEvent actionEvent) {
        Item item = new Item(
                txtCode.getText(),txtDescription.getText()
                ,Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );
        try {
            boolean isItemSaved= new DatabaseAccessCode().saveItem(item);
            if (isItemSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Saved").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            throw new RuntimeException(e);
        }
    }
}
