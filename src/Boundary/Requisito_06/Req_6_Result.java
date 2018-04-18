package Boundary.Requisito_06;

import Control.Controller;
import Entity.Structure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Req_6_Result {
    /*
    TODO completare
     */

    @FXML
    private javafx.scene.control.TableView<Structure> table = new TableView<>();
    @FXML
    private TableColumn<Structure, String> columnID = new TableColumn<>("id");
    @FXML
    private TableColumn<Structure, String> columnName = new TableColumn<>("name");
    @FXML
    private static ObservableList<Structure> F = FXCollections.observableArrayList();
    @FXML
    private static String beanID;
    @FXML
    private static String beanName;
    @FXML
    private Button Req3PageBackButton;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnBack;

    public void start() throws Exception {
        Stage thirdStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Boundary/Requisito_06/req_6_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 800, 800);
        //thirdStage.setScene(new Scene(root, 488, 277));

        columnID.setMinWidth(140);
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));

        columnName.setMinWidth(140);
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));


        table.setPrefSize(437, 510);
        table.setLayoutX(10);
        table.setLayoutY(10);
        ((AnchorPane) scene.getRoot()).getChildren().addAll(table);
        table.setItems(F);
        table.getColumns().addAll(columnID, columnName);
        thirdStage.setScene(scene);
        thirdStage.show();
    }

    public void parseStructures(ArrayList<Structure> structures, String id, String name) {
        Controller controller = new Controller();
        beanID=id;
        beanName=name;

        //F.add(controller.createBound(Integer.parseInt(beanID), Double.parseDouble(beanLon), Double.parseDouble(beanLat)));
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));


        table.setItems(F);
    }


    public void nextTwenty(javafx.event.ActionEvent actionEvent) {
    }
}
