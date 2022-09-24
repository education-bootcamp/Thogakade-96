package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.ItemDto;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.ItemTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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

    private ItemBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.ITEM);

    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadItems();

        //==========listeners
        tblItem.getSelectionModel()
                .selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    setData(newValue);
                }));

    }

    private void setData(ItemTm tm) {
        txtCode.setText(tm.getCode());
        txtDescription.setText(tm.getDescription());
        txtUnitPrice.setText(String.valueOf(tm.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(tm.getQtyOnHand()));
        btnSaveItem.setText("Update Item");
    }

    private void loadItems() {
        ObservableList<ItemTm> tmList= FXCollections.observableArrayList();
        try {
            for (ItemDto i : bo.loadAllItems()){
                Button btn = new Button("Delete");
                ItemTm tm = new ItemTm(i.getCode(),i.getDescription(),
                        i.getUnitPrice(),i.getQtyOnHand(),btn);
                tmList.add(tm);

                btn.setOnAction(e->{
                    try{
                        Alert alert= new Alert(Alert.AlertType.CONFIRMATION,
                                "Are you sure?", ButtonType.YES, ButtonType.NO);
                        Optional<ButtonType> buttonType = alert.showAndWait();
                        if (buttonType.get()==ButtonType.YES){
                            if (bo.deleteItem(tm.getCode())){
                                new Alert(Alert.AlertType.CONFIRMATION,"Item Deleted!").show();
                                loadItems();
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
            tblItem.setItems(tmList);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) itemFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(
                getClass().getResource("../view/MainForm.fxml"))));
        stage.centerOnScreen();
    }

    public void newItemOnAction(ActionEvent actionEvent) {
        clear();
        btnSaveItem.setText("Save Item");
    }

    public void saveItemOnAction(ActionEvent actionEvent) {
        ItemDto item = new ItemDto(
                txtCode.getText(),txtDescription.getText()
                ,Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );

        if (btnSaveItem.getText().equalsIgnoreCase("Save Item")){
            try {
                boolean isItemSaved= bo.saveItem(item);
                if (isItemSaved){
                    loadItems();
                    clear();
                    new Alert(Alert.AlertType.CONFIRMATION,"Item Saved").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
                throw new RuntimeException(e);
            }
        }else{
            try {
                boolean isItemUpdated= bo.updateItem(item);
                if (isItemUpdated){
                    loadItems();
                    clear();
                    new Alert(Alert.AlertType.CONFIRMATION,"Item Updated").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
                throw new RuntimeException(e);
            }
        }


    }

    private void clear(){
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }
}
