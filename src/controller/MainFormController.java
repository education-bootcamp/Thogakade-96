package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public AnchorPane mainFormContext;

    public void btnItemManagementOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ItemForm");
    }

    public void btnCustomerManagementOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) mainFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(
                getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
