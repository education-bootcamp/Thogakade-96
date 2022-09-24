package controller;

import dao.DatabaseAccessCode;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.ItemTm;

import java.sql.SQLException;

public class ItemFormController {
    public AnchorPane itemFormContext;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public Button btnSaveItem;
    public TableView<ItemTm> tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colOption;

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadItems();
    }

    private void loadItems() {
        ObservableList<ItemTm> tmList= FXCollections.observableArrayList();
        try {
            for (Item i : new DatabaseAccessCode().loadAllItems()){
                Button btn = new Button("Delete");
                ItemTm tm = new ItemTm(i.getCode(),i.getDescription(),
                        i.getUnitPrice(),i.getQtyOnHand(),btn);
                tmList.add(tm);
            }
            tblItem.setItems(tmList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

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
