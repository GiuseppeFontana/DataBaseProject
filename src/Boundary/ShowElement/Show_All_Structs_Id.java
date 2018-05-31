package Boundary.ShowElement;

import Bean.BeanId;
import Boundary.Requisito_11.Req_11_Page;
import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Singletons.SingletonId;
import javafx.application.Platform;
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

public class Show_All_Structs_Id {
    @FXML
    private Button btnTurnBack;
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
    private Label labelSat;


    @FXML
    private javafx.scene.control.TableView<BeanId> tableView = new TableView<>();
    @FXML
    private TableColumn<BeanId, Integer> columnId = new TableColumn<>("id");
    @FXML
    private static ObservableList<BeanId> list = FXCollections.observableArrayList();

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
        int size = SingletonId.getInstance().getBeans().size();
        if (size %20 != 0){
            setnTotalPages(size/20 + 1);
        }
        else {
            setnTotalPages(size/20);
        }

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Show_All_Structs_Id.class.getResource("show_all_structs_id.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 500, 550 );

        labelSat = new Label();
        labelSat.relocate(250,100);
        labelSat.setText("Id disponibili per\nil satellite "+SingletonId.getInstance().getSatellite());

        labelCurrentPage = new Label();
        labelCurrentPage.relocate(250, 350);
        labelCurrentPage.setText("pag. "+ getnCurrentPage() + "di "+ getnTotalPages());

        buttonBegin.relocate(250, 200);
        buttonM10.relocate(250, 230);
        buttonPrev.relocate(250, 260);
        buttonNext.relocate(330, 260);
        buttonP10.relocate(330, 230);
        buttonEnd.relocate(330, 200);

        root.getChildren().addAll(buttonBegin);
        root.getChildren().addAll(buttonM10);
        root.getChildren().addAll(buttonNext);
        root.getChildren().addAll(buttonPrev);
        root.getChildren().addAll(buttonP10);
        root.getChildren().addAll(buttonEnd);
        root.getChildren().addAll(labelCurrentPage);
        root.getChildren().addAll(labelSat);

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


        tableView.setPrefSize(150, 510);
        tableView.setLayoutX(50);
        tableView.setLayoutY(20);

        ((AnchorPane) scene.getRoot()).getChildren().addAll(tableView);
        tableView.setItems(list);
        tableView.getColumns().addAll(columnId);

        riempi();

        /*tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            String satellite = tableView.getSelectionModel().getSelectedItem().getSatellite();
            int id = tableView.getSelectionModel().getSelectedItem().getId();
            DBController dbController = new DBController();
            try {
                dbController.showStruct(id, satellite);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));*/

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    private void riempi() {
        if (getnTotalPages() != getnCurrentPage() || (getnTotalPages() == getnCurrentPage() && SingletonId.getInstance().getBeans().size() %20 == 0)) {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + 20; i++) {
                parseBean(SingletonId.getInstance().getBeans().get(i).getId());
            }
        }
        else {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + SingletonId.getInstance().getBeans().size() %20; i++) {
                parseBean(SingletonId.getInstance().getBeans().get(i).getId());
            }
        }
    }

    public void parseBean(Integer id){
        Controller controller = new Controller();
        list.add(controller.createBeanId(id));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.setItems(list);
    }


    public void close(ActionEvent actionEvent) throws Exception{
        list.clear();
        Controller controller = new Controller();
        controller.resetSingletonId();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void next(ActionEvent actionEvent) throws Exception{
        if (getnCurrentPage()<getnTotalPages()){
            setnCurrentPage(getnCurrentPage()+1);
            list.clear();
            riempi();
        }
    }

    public void prev(ActionEvent actionEvent) throws Exception{
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
