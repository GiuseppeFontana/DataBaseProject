package Boundary.Requisito_12;

import Bean.Req12_BeanToShow;
import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Singletons.SingletonReq12;
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

import java.util.Comparator;

public class Req_12_Result {

    @FXML
    private static int nCurrentPage;
    @FXML
    private static int nTotalPages;
    @FXML
    private Label labelCurrentPage;

    @FXML
    private javafx.scene.control.TableView<Req12_BeanToShow> tableView = new TableView<>();
    @FXML
    private TableColumn<Req12_BeanToShow, Integer> columnId = new TableColumn<>("id");
    @FXML
    private TableColumn<Req12_BeanToShow, Double> columnFlux = new TableColumn<>("flusso");
    @FXML
    private TableColumn<Req12_BeanToShow, Double> columnDistance = new TableColumn<>("distanza");
    @FXML
    private static ObservableList<Req12_BeanToShow> list = FXCollections.observableArrayList();

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
    private Button btnSortFlux = new Button("Flusso");
    @FXML
    private Button sortDistance = new Button("Distanza");


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
        int size = SingletonReq12.getInstance().getBeanToShows().size();


        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("req_12_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        labelCurrentPage = new Label();
        labelCurrentPage.relocate(522, 583);
        labelCurrentPage.setText("pag. "+ getnCurrentPage() + " di "+ getnTotalPages());

        buttonBegin.relocate(140, 580);
        buttonM10.relocate(200, 580);
        buttonPrev.relocate(250, 580);
        buttonNext.relocate(285, 580);
        buttonP10.relocate(325, 580);
        buttonEnd.relocate(375, 580);

        sortDistance.relocate(566, 373);
        btnSortFlux.relocate(567, 325);

        root.getChildren().addAll(buttonBegin);
        root.getChildren().addAll(buttonM10);
        root.getChildren().addAll(buttonNext);
        root.getChildren().addAll(buttonPrev);
        root.getChildren().addAll(buttonP10);
        root.getChildren().addAll(buttonEnd);

        root.getChildren().addAll(sortDistance);
        root.getChildren().addAll(btnSortFlux);

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

        btnSortFlux.setOnAction(event -> {
            SingletonReq12.getInstance().getBeanToShows().sort(Comparator.comparingDouble(Req12_BeanToShow::getFlux));
            list.clear();
                riempi();

        });

        sortDistance.setOnAction(event -> {
            SingletonReq12.getInstance().getBeanToShows().sort(Comparator.comparingDouble(Req12_BeanToShow::getDistance));
            list.clear();
            riempi();
        });

        //root.getChildren().addAll(labelCurrentPage);

        setnCurrentPage(1);

        if (size %20 != 0){
            setnTotalPages(size/20 + 1);
        }
        else {
            setnTotalPages(size/20);
        }

        columnId.setMinWidth(70);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        columnFlux.setMinWidth(90);
        columnFlux.setCellValueFactory(new PropertyValueFactory<>("flux"));

        columnDistance.setMinWidth(250);
        columnDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));

        columnFlux.setSortable(false);
        columnDistance.setSortable(false);
        columnId.setSortable(false);

        tableView.setPrefSize(420, 510);
        tableView.setLayoutX(50);
        tableView.setLayoutY(60);

        ((AnchorPane) scene.getRoot()).getChildren().addAll(tableView);
        tableView.setItems(list);
        tableView.getColumns().addAll(columnId, columnFlux, columnDistance);
        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            int id = tableView.getSelectionModel().getSelectedItem().getId();
            DBController dbController = new DBController();
            try {
                dbController.showStar(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        riempi();

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    private void riempi() {
        if (getnTotalPages() != getnCurrentPage() || (getnTotalPages() == getnCurrentPage() && SingletonReq12.getInstance().getBeanToShows().size() %20 == 0)) {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + 20; i++) {
                parseBean(SingletonReq12.getInstance().getBeanToShows().get(i).getId(),
                        SingletonReq12.getInstance().getBeanToShows().get(i).getFlux(),
                        SingletonReq12.getInstance().getBeanToShows().get(i).getDistance());
            }
        }
        else {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + SingletonReq12.getInstance().getBeanToShows().size() %20; i++) {
                parseBean(SingletonReq12.getInstance().getBeanToShows().get(i).getId(),
                        SingletonReq12.getInstance().getBeanToShows().get(i).getFlux(),
                        SingletonReq12.getInstance().getBeanToShows().get(i).getDistance());
            }
        }
        System.out.println("Pagina " + getnCurrentPage() + " di " + getnTotalPages());


    }

    public void parseBean(Integer id, double flux, double distance){

        Controller controller = new Controller();
        list.add(controller.createReq12_BeanToShow(id, flux, distance));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFlux.setCellValueFactory(new PropertyValueFactory<>("flux"));
        columnDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
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

    public void next10(ActionEvent actionEvent) throws Exception{
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

    public void prev10(ActionEvent actionEvent) throws Exception{
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

    public void begin(ActionEvent actionEvent) throws Exception{
        setnCurrentPage(1);
        list.clear();
        riempi();
    }

    public void end(ActionEvent actionEvent) throws Exception{
        setnCurrentPage(getnTotalPages());
        list.clear();
        riempi();
    }
}
