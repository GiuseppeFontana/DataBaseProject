package Boundary.Requisito_11;

import Bean.Req11_Bean;
import Boundary.Alerts.Alert;
import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Singletons.SingletonReq11;
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


public class Req_11_Result {

    @FXML
    private static int nCurrentPage;
    @FXML
    private static int nTotalPages;

    @FXML
    private javafx.scene.control.TableView<Req11_Bean> tableView = new TableView<>();
    @FXML
    private TableColumn<Req11_Bean, Integer> columnName = new TableColumn<>("segmenti");
    @FXML
    private static ObservableList<Req11_Bean> list = FXCollections.observableArrayList();

    @FXML
    private Label labelCurrentPage;
    @FXML
    private Label labelTotal;
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

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_11_Result.class.getResource("req_11_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649);

        columnName.setMinWidth(140);
        columnName.setCellValueFactory(new PropertyValueFactory<>("segmenti"));

        columnName.setSortable(false);

        ((AnchorPane) scene.getRoot()).getChildren().addAll(tableView);
        tableView.setItems(list);
        tableView.getColumns().addAll(columnName);
        tableView.setPrefSize(142, 510);
        tableView.setLayoutX(50);
        tableView.setLayoutY(25);

        labelCurrentPage = new Label();
        labelCurrentPage.relocate(522, 583);

        int size = SingletonReq11.getInstance().getBeans().size();

        labelTotal = new Label();
        labelTotal.relocate(560, 583);
        labelTotal.setText(" di  "+ SingletonReq11.getInstance().getBeans().size());


        setnCurrentPage(1);

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

        if (size %20 != 0){
            setnTotalPages(size/20 + 1);
        }
        else {
            setnTotalPages(size/20);
        }

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        riempi();

        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            try {
                int segmento = tableView.getSelectionModel().getSelectedItem().getSegmenti();
                DBController dbController = new DBController();
                Alert alert = new Alert();
                alert.incorrectLoginField("Distanza minima primo vertice \n\n" +
                        String.valueOf(dbController.Req11_distance(segmento).get(0)).substring(8) +
                "\n\n Distanza minima secondo vertice \n\n" +
                        String.valueOf(dbController.Req11_distance(segmento).get(1)).substring(8));

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }));
    }

    public void riempi(){

        if (getnTotalPages() != getnCurrentPage() || (getnTotalPages() == getnCurrentPage() &&
                SingletonReq11.getInstance().getBeans().size() %20 == 0)) {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + 20; i++) {
                parseReq11_Bean(SingletonReq11.getInstance().getBeans().get(i));
            }
        }
        else {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) +
                    SingletonReq11.getInstance().getBeans().size() %20; i++) {
                parseReq11_Bean(SingletonReq11.getInstance().getBeans().get(i));
            }
        }
        System.out.println("Pagina " + getnCurrentPage() + " di " + getnTotalPages());
        }

    public void parseReq11_Bean(Req11_Bean segmento){

        list.add(segmento);
        columnName.setCellValueFactory(new PropertyValueFactory<>("segmenti"));
        tableView.setItems(list);


    }

    public void backHome(ActionEvent actionEvent) throws Exception {
        list.clear();

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        controller.resetAllSingleton();
        boolean admin = controller.getUserSingleton().getUser().getAdmin();
        if (!admin) {
            GraphicController graphicController = new GraphicController();
            graphicController.req11page();
        }
        if (admin) {
            GraphicController graphicController = new GraphicController();
            graphicController.req11page();
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
