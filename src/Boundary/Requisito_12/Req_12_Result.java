package Boundary.Requisito_12;

import Bean.Req9_10Bean;
import Boundary.Alert;
import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Singletons.SingletonReq12;
import Singletons.SingletonReq9;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

import static Boundary.Requisito_12.Req_12_Page.sat;

public class Req_12_Result {

    @FXML
    private static int nCurrentPage;
    @FXML
    private static int nTotalPages;
    @FXML
    private Label labelTotal;
    @FXML
    private Label labelCurrentPage;

    @FXML
    private javafx.scene.control.TableView<Req9_10Bean> tableView = new TableView<>();
    @FXML
    private TableColumn<Req9_10Bean, Integer> columnId = new TableColumn<>("id");
    @FXML
    private TableColumn<Req9_10Bean, String> columnName = new TableColumn<>("name");
    @FXML
    private static ObservableList<Req9_10Bean> list = FXCollections.observableArrayList();

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

        Controller controller = new Controller();
        controller.calcolaTipi9();

        System.out.println("unbound: " + SingletonReq9.getInstance().getUnbound() +
                "\nprestellar: " + SingletonReq9.getInstance().getPrestellar() +
                "\nprotostellar: " + SingletonReq9.getInstance().getProtostellar());

        setnCurrentPage(1);
        int size = SingletonReq9.getInstance().getBeans().size();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("req_12_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 800, 649 );

        labelTotal = new Label();
        labelTotal.relocate(560, 583);
        labelTotal.setText(" di  "+ SingletonReq9.getInstance().getBeans().size());

        labelCurrentPage = new Label();
        labelCurrentPage.relocate(522, 583);
        labelCurrentPage.setText("1");

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

        root.getChildren().addAll(labelCurrentPage);

        setnCurrentPage(1);

        if (size %20 != 0){
            setnTotalPages(size/20 + 1);
        }
        else {
            setnTotalPages(size/20);
        }

        columnId.setMinWidth(140);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        columnName.setMinWidth(140);
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.setPrefSize(540, 510);
        tableView.setLayoutX(100);
        tableView.setLayoutY(65);

        ((AnchorPane) scene.getRoot()).getChildren().addAll(tableView);
        tableView.setItems(list);
        tableView.getColumns().addAll(columnId, columnName);
        riempi();

        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {

            int id = tableView.getSelectionModel().getSelectedItem().getId();
            DBController dbController = new DBController();
            try {
                dbController.Req12_Distance(sat, id);
                String dist = SingletonReq12.getInstance().getBeans().getDistance();
                Double flux = SingletonReq12.getInstance().getBeans().getFlux();
                Alert alert = new Alert();
                alert.incorrectLoginField("Distanza dalla spina dorsale =\n\n" + String.valueOf(dist) +
                "\n\nValore del flusso della sorgente =\n\n" + flux);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }));

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    private void riempi() {
        if (getnTotalPages() != getnCurrentPage() || (getnTotalPages() == getnCurrentPage() && SingletonReq9.getInstance().getBeans().size() %20 == 0)) {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + 20; i++) {
                parseBean(SingletonReq9.getInstance().getBeans().get(i).getId(),
                        SingletonReq9.getInstance().getBeans().get(i).getName());
            }
        }
        else {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + SingletonReq9.getInstance().getBeans().size() %20; i++) {
                parseBean(SingletonReq9.getInstance().getBeans().get(i).getId(),
                        SingletonReq9.getInstance().getBeans().get(i).getName());
            }
        }
        System.out.println("Pagina " + getnCurrentPage() + " di " + getnTotalPages());

    }

    public void parseBean(Integer id, String name){
        Controller controller = new Controller();
        list.add(controller.createReq9_10Bean(id, name));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setItems(list);
    }

    public void backHome(ActionEvent actionEvent) throws Exception{
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        controller.resetSingleton9();
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
