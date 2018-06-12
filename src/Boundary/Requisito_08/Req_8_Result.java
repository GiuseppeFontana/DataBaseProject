package Boundary.Requisito_08;

import Bean.Req6_8SquareBean;
import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Singletons.SingletonReq7;
import Singletons.SingletonReq8;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_8_Result {

    @FXML
    private static int nCurrentPage;
    @FXML
    private static int nTotalPages;

    @FXML
    private Button buttonNext = new Button(">");
    @FXML
    private Button buttonPrev = new Button("<");
    @FXML
    private Button buttonP10 = new Button(">>");
    @FXML
    private Button buttonM10 = new Button("<<");
    @FXML
    private Button buttonBegin = new Button("inizio");
    @FXML
    private Button buttonEnd = new Button("fine");
    @FXML
    private Label labelCurrentPage;


    @FXML
    private javafx.scene.control.TableView<Req6_8SquareBean> tableView = new TableView<>();
    @FXML
    private TableColumn<Req6_8SquareBean, Integer> columnId = new TableColumn<>("id");
    @FXML
    private TableColumn<Req6_8SquareBean, String> columnName = new TableColumn<>("name");
    @FXML
    private TableColumn<Req6_8SquareBean, String> columnSatellite = new TableColumn<>("satellite");
    @FXML
    private static ObservableList<Req6_8SquareBean> list = FXCollections.observableArrayList();

    public int getnCurrentPage() {
        return nCurrentPage;
    }

    public void setnCurrentPage(int n) {
        nCurrentPage = n;
    }

    public int getnTotalPages() {
        return nTotalPages;
    }

    public void setnTotalPages(int n) {
        nTotalPages = n;
    }

    public void start() throws Exception{

        setnCurrentPage(1);
        int size = SingletonReq8.getInstance().getBeans().size();
        if (size %20 != 0){
            setnTotalPages(size/20 + 1);
        }
        else {
            setnTotalPages(size/20);
        }

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_8_Result.class.getResource("req_8_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );


        labelCurrentPage = new Label();
        labelCurrentPage.relocate(520, 583);
        labelCurrentPage.setText("pag. "+ getnCurrentPage() + " di "+ getnTotalPages());

        buttonBegin.relocate(140, 580);
        buttonM10.relocate(200, 580);
        buttonPrev.relocate(250, 580);
        buttonNext.relocate(285, 580);
        buttonP10.relocate(325, 580);
        buttonEnd.relocate(375, 580);

        root.getChildren().addAll(buttonBegin);
        root.getChildren().addAll(buttonM10);
        root.getChildren().addAll(buttonNext);
        root.getChildren().addAll(buttonPrev);
        root.getChildren().addAll(buttonP10);
        root.getChildren().addAll(buttonEnd);
        root.getChildren().addAll(labelCurrentPage);

        buttonBegin.setOnAction(event -> {
            try {
                begin(event);
                labelCurrentPage.setText("pag. "+ getnCurrentPage() + " di "+ getnTotalPages());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonM10.setOnAction(event -> {
            try {
                prev10(event);
                labelCurrentPage.setText("pag. "+ getnCurrentPage() + " di "+ getnTotalPages());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonPrev.setOnAction(event -> {
            try {
                prev(event);
                labelCurrentPage.setText("pag. "+ getnCurrentPage() + " di "+ getnTotalPages());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonNext.setOnAction(event -> {
            try {
                next(event);
                labelCurrentPage.setText("pag. "+ getnCurrentPage() + " di "+ getnTotalPages());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonP10.setOnAction(event -> {
            try {
                next10(event);
                labelCurrentPage.setText("pag. "+ getnCurrentPage() + " di "+ getnTotalPages());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonEnd.setOnAction(event -> {
            try {
                end(event);
                labelCurrentPage.setText("pag. "+ getnCurrentPage() + " di "+ getnTotalPages());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        columnId.setMinWidth(140);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        columnName.setMinWidth(140);
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        columnSatellite.setMinWidth(140);
        columnSatellite.setCellValueFactory(new PropertyValueFactory<>("satellite"));

        columnId.setSortable(false);
        columnName.setSortable(false);
        columnSatellite.setSortable(false);

        tableView.setPrefSize(437, 510);
        tableView.setLayoutX(100);
        tableView.setLayoutY(65);

        ((AnchorPane) scene.getRoot()).getChildren().addAll(tableView);
        tableView.setItems(list);
        tableView.getColumns().addAll(columnId,columnName, columnSatellite);

        riempi();

        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            String satellite = tableView.getSelectionModel().getSelectedItem().getSatellite();
            int id = tableView.getSelectionModel().getSelectedItem().getId();
            DBController dbController = new DBController();
            try {
                dbController.showStruct(id, satellite);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    private void riempi() {
        if (getnTotalPages() != getnCurrentPage() || (getnTotalPages() == getnCurrentPage() &&
                SingletonReq8.getInstance().getBeans().size() %20 == 0)) {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + 20; i++) {
                parseBean(SingletonReq8.getInstance().getBeans().get(i).getId(),
                        SingletonReq8.getInstance().getBeans().get(i).getName(),
                        SingletonReq8.getInstance().getBeans().get(i).getSatellite());
            }
        }
        else {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) +
                    SingletonReq8.getInstance().getBeans().size() %20; i++) {
                parseBean(SingletonReq8.getInstance().getBeans().get(i).getId(),
                        SingletonReq8.getInstance().getBeans().get(i).getName(),
                        SingletonReq8.getInstance().getBeans().get(i).getSatellite());
            }
        }
    }

    public void parseBean(Integer id, String name, String satellite){
        Controller controller = new Controller();
        list.add(controller.createReq6_8Bean(id,name,satellite));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSatellite.setCellValueFactory(new PropertyValueFactory<>("satellite"));
        tableView.setItems(list);
    }

    public void backHome(ActionEvent actionEvent) throws Exception{
        list.clear();

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        controller.resetAllSingleton();
        boolean admin = controller.getUserSingleton().getUser().getAdmin();
        if(!admin){
            GraphicController graphicController = new GraphicController();
            graphicController.homeUser();
        }
        if (admin){
            GraphicController graphicController = new GraphicController();
            graphicController.homeAdmin();
        }
    }

    public void next(ActionEvent actionEvent){
        if (getnCurrentPage()<getnTotalPages()){
            setnCurrentPage(getnCurrentPage()+1);
            list.clear();
            riempi();
        }
    }

    public void prev(ActionEvent actionEvent){
        if (getnCurrentPage()>1){
            setnCurrentPage(getnCurrentPage()-1);
            list.clear();
            riempi();
        }
    }

    public void next10(ActionEvent actionEvent){
        if (getnCurrentPage()<getnTotalPages()-10){
            setnCurrentPage(getnCurrentPage()+10);
            list.clear();
            riempi();
        }
        else {
            setnCurrentPage(getnTotalPages());
            list.clear();
            riempi();
        }
    }

    public void prev10(ActionEvent actionEvent){
        if (getnCurrentPage() > 10){
            setnCurrentPage(getnCurrentPage()-10);
            list.clear();
            riempi();
        }
        else {
            setnCurrentPage(1);
            list.clear();
            riempi();
        }
    }

    public void begin(ActionEvent actionEvent){
        setnCurrentPage(1);
        list.clear();
        riempi();
    }

    public void end(ActionEvent actionEvent){
        setnCurrentPage(getnTotalPages());
        list.clear();
        riempi();
    }
}
