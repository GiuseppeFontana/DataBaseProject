package Boundary;

import Control.Controller;
import Control.DBController;
import Entity.Bound;
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

import java.awt.event.ActionEvent;

public class Req_7_Result {
    /*
    TODO sostituire bound con struttura per soddisfare il requisito
     */

    /*@FXML
    private javafx.scene.control.TableView<Bound> table = new TableView<>();
    @FXML
    private TableColumn<Bound, String> columnID = new TableColumn<>("id");
    @FXML
    private TableColumn<Bound, String> columnLon = new TableColumn<>("lon");
    @FXML
    private TableColumn<Bound, String> columnLat = new TableColumn<>("lat");

    @FXML
    private static ObservableList<Bound> F = FXCollections.observableArrayList();
    @FXML
    private static String beanID;
    @FXML
    private static String beanLat;
    @FXML
    private static String beanLon;


    @FXML
    private Button Req3PageBackButton;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnBack;

    public void start() throws Exception {
        Stage thirdStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/req_7_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 800, 800);
        //thirdStage.setScene(new Scene(root, 488, 277));

        columnID.setMinWidth(140);
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));

        columnLat.setMinWidth(140);
        columnLat.setCellValueFactory(new PropertyValueFactory<>("lat"));

        columnLon.setMinWidth(140);
        columnLon.setCellValueFactory(new PropertyValueFactory<>("lon"));

        table.setPrefSize(437, 510);
        table.setLayoutX(10);
        table.setLayoutY(10);
        ((AnchorPane) scene.getRoot()).getChildren().addAll(table);
        table.setItems(F);
        table.getColumns().addAll(columnID, columnLat, columnLon);
        thirdStage.setScene(scene);
        thirdStage.show();
    }

    public void parseStar(String id, String lat, String lon) {
        Controller controller = new Controller();
        beanID=id;
        beanLat=lat;
        beanLon = lon;
        F.add(controller.createBound(Integer.parseInt(beanID), Double.parseDouble(beanLon), Double.parseDouble(beanLat)));
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnLat.setCellValueFactory(new PropertyValueFactory<>("lat"));
        columnLon.setCellValueFactory(new PropertyValueFactory<>("lon"));

        table.setItems(F);
    }


    public void nextTwenty(javafx.event.ActionEvent actionEvent) {

        int counter = 1;

    }*/
}
