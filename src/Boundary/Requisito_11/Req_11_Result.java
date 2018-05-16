package Boundary.Requisito_11;

import Bean.Req11_Bean;
import Boundary.Alert;
import Control.DBController;
import Singletons.SingletonReq11;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Req_11_Result {

    @FXML
    private javafx.scene.control.TableView<Req11_Bean> tableView = new TableView<>();
    @FXML
    private TableColumn<Req11_Bean, Integer> columnName = new TableColumn<>("segmenti");
    @FXML
    private static ObservableList<Req11_Bean> list = FXCollections.observableArrayList();

    public void start() throws Exception{

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_11_Result.class.getResource("segment_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649);

        columnName.setMinWidth(140);
        columnName.setCellValueFactory(new PropertyValueFactory<>("segmenti"));

        ((AnchorPane) scene.getRoot()).getChildren().addAll(tableView);
        tableView.setItems(list);
        tableView.getColumns().addAll(columnName);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        riempi();

        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            try {
                int segmento = tableView.getSelectionModel().getSelectedItem().getSegmenti();
                DBController dbController = new DBController();
                String total2 = String.valueOf(dbController.Req11_distance(segmento));
                Alert alert = new Alert();
                alert.incorrectLoginField("Distanza minima = \n" + total2.substring(8));

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }));
    }

    public void riempi(){

        for (int i = 0; i < SingletonReq11.getInstance().getBeans().size(); i++){
            parseReq11_Bean(SingletonReq11.getInstance().getBeans().get(i));
        }
    }

    public void parseReq11_Bean(Req11_Bean segmento){

        list.add(segmento);
        columnName.setCellValueFactory(new PropertyValueFactory<>("segmenti"));
        tableView.setItems(list);


    }

}
