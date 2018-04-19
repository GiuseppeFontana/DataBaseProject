package Prove;

import Prove.Req6Bean;
import Control.DBController;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Prova2Req6res implements Initializable {
    @FXML
    public Button ButtonLoad;
    @FXML
    public Button buttonNext;
    @FXML
    public Button buttonPrev;
    @FXML
    private javafx.scene.control.TableView<Req6Bean> tableView = new TableView<>();
    @FXML
    private TableColumn<Req6Bean, Integer> columnId = new TableColumn<>("id");
    @FXML
    private TableColumn<Req6Bean, String> columnName = new TableColumn<>("name");
    @FXML
    private TableColumn<Req6Bean, String> columnSatellite = new TableColumn<>("satellite");
    @FXML
    private static ObservableList<Req6Bean> list = FXCollections.observableArrayList();
    @FXML
    private ArrayList<Req6Bean> req6Beans;

    @FXML
    private static Integer structureId;
    @FXML
    private static String structureName;
    @FXML
    private static String structureSatellite;

    @FXML
    private static int counterPage;
    @FXML
    private static int totalPages;

    @FXML
    private double percBrillanza1;
    @FXML
    private double elliptMin1;
    @FXML
    private double elliptMax1;

    @FXML
    private Label label = new Label("Pagina ");

    public int getCounterPage() {
        return counterPage;
    }

    public void setCounterPage(int c) {
        counterPage = c;
    }

    public static int getTotalPages() {
        return totalPages;
    }

    public static void setTotalPages(int total) {
        totalPages = total;
    }

    public void start(double percBrillanza, double elliptMin, double elliptMax) throws  Exception{
        percBrillanza1 = percBrillanza;
        elliptMin1 = elliptMin;
        elliptMax1 = elliptMax;
        setCounterPage(1);

        label.relocate(481, 440);

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Prove/prova2_req6res.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 600, 800);

        DBController dbController = new DBController();
        dbController.ricercaReq6(percBrillanza1, elliptMin1, elliptMax1);


        columnId.setMinWidth(140);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        columnName.setMinWidth(140);
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        columnSatellite.setMinWidth(140);
        columnSatellite.setCellValueFactory(new PropertyValueFactory<>("satellite"));

        tableView.setPrefSize(437, 510);
        tableView.setLayoutX(10);
        tableView.setLayoutY(10);

        ((AnchorPane) scene.getRoot()).getChildren().addAll(tableView);
        tableView.setItems(list);
        tableView.getColumns().addAll(columnId,columnName, columnSatellite);
               

        stage.setScene(scene);
        stage.show();
    }
    

    public void parseBean(Integer id, String name, String satellite, Integer totaleStrutture){
        structureId = id;
        structureName = name;
        structureSatellite = satellite;
        list.add(new Req6Bean(id,name,satellite, totaleStrutture));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSatellite.setCellValueFactory(new PropertyValueFactory<>("satellite"));
        tableView.setItems(list);
        
        //inizio
        
        //fine
    }
    
    
    

    public void next(ActionEvent actionEvent) {

        if (getCounterPage()<getTotalPages()){
            setCounterPage(getCounterPage()+1);
            list.clear();
            DBController dbController = new DBController();
            dbController.nextResult(counterPage);
        }

        /*buttonNext.setOnAction(event -> {
            System.out.println("before: "+getCounterPage());
            setCounterPage(getCounterPage()+1);
            System.out.println("after: "+getCounterPage());

            label.setText("Pagina: " + Integer.toString(getCounterPage()));
        });*/
    }

    public void prev(ActionEvent actionEvent) {
        if (getCounterPage()>1){
            setCounterPage(getCounterPage()-1);
            list.clear();
            DBController dbController = new DBController();
            dbController.nextResult(counterPage);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        tableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1){
                System.out.println("clicked");
            }
        });        
    }
    
    /*private void onEdit(){
        
        if (tableView.getSelectionModel().getSelectedItem() != null){
            Req6Bean req6Bean = tableView.getSelectionModel().getSelectedItem();
            
        }
    }*/

    /*public void load(ActionEvent actionEvent) throws Exception{
        DBController dbController = new DBController();
        dbController.ricercaReq6(percBrillanza1, elliptMin1, elliptMax1);

    }*/
}
