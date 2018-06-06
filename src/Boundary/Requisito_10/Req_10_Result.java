package Boundary.Requisito_10;

import Bean.Req9_10Bean;
import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Singletons.SingletonReq10;
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

public class Req_10_Result {
    @FXML
    private Button BackButton;

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
    private Label label0;
    @FXML
    private Label label1;

    @FXML
    private Label labelUnbound0;
    @FXML
    private Label labelPrestellar0;
    @FXML
    private Label labelProtostellar0;

    @FXML
    private Label labelUnbound1;
    @FXML
    private Label labelPrestellar1;
    @FXML
    private Label labelProtostellar1;

    @FXML
    private javafx.scene.control.TableView<Req9_10Bean> tableView = new TableView<>();
    @FXML
    private TableColumn<Req9_10Bean, Integer> columnId = new TableColumn<>("id");
    @FXML
    private TableColumn<Req9_10Bean, String> columnName = new TableColumn<>("name");
    @FXML
    private static ObservableList<Req9_10Bean> list = FXCollections.observableArrayList();


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


    public void backHome(ActionEvent actionEvent) throws Exception{
        list.clear();

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        controller.resetSingleton10();
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

    public void start() throws Exception{

        Controller controller = new Controller();
        controller.calcolaTipi10();


        setnCurrentPage(1);
        int size = SingletonReq10.getInstance().getBeansToShow().size();
        if (size %20 != 0){
            setnTotalPages(size/20 + 1);
        }
        else {
            setnTotalPages(size/20);
        }

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_10_Result.class.getResource("req_10_result.fxml"));
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

        label0 = new Label();
        label0.relocate(450, 150);
        label0.setText("Total out of structs: "+ controller.round(
                (double)SingletonReq10.getInstance().getTotal_false()/
                        (double)(SingletonReq10.getInstance().getBeansToShow().size())*100.0) + " %");
        root.getChildren().addAll(label0);

        labelUnbound0 = new Label();
        labelUnbound0.relocate(450, 170);
        labelUnbound0.setText("Unbound out of structs: "+ controller.round(
                (double)SingletonReq10.getInstance().getUnbound_false()/
                        (double)(SingletonReq10.getInstance().getTotal_false())*100.0) + " %");
        root.getChildren().addAll(labelUnbound0);

        labelPrestellar0 = new Label();
        labelPrestellar0.relocate(450, 190);
        labelPrestellar0.setText("Prestellar out of structs: "+controller.round(
                (double)SingletonReq10.getInstance().getPrestellar_false()/
                        (double)(SingletonReq10.getInstance().getTotal_false())*100.0) + " %");
        root.getChildren().addAll(labelPrestellar0);

        labelProtostellar0 = new Label();
        labelProtostellar0.relocate(450, 210);
        labelProtostellar0.setText("Protostellar out of structs: "+controller.round(
                (double)SingletonReq10.getInstance().getProtostellar_false()/
                        (double)(SingletonReq10.getInstance().getTotal_false())*100.0) + " %");
        root.getChildren().addAll(labelProtostellar0);

        label1 = new Label();
        label1.relocate(450, 250);
        label1.setText("Total in structs: "+ controller.round(
                (double)SingletonReq10.getInstance().getTotal_true()/
                        (double)(SingletonReq10.getInstance().getBeansToShow().size())*100.0) + " %");
        root.getChildren().addAll(label1);

        labelUnbound1 = new Label();
        labelUnbound1.relocate(450, 270);
        labelUnbound1.setText("Unbound in structs: "+ controller.round(
                (double)SingletonReq10.getInstance().getUnbound_true()/
                        (double)(SingletonReq10.getInstance().getTotal_true())*100.0) + " %");
        root.getChildren().addAll(labelUnbound1);

        labelPrestellar1 = new Label();
        labelPrestellar1.relocate(450, 290);
        labelPrestellar1.setText("Prestellar in structs: "+ controller.round(
                (double)SingletonReq10.getInstance().getPrestellar_true()/
                        (double)(SingletonReq10.getInstance().getTotal_true())*100.0) + " %");
        root.getChildren().addAll(labelPrestellar1);

        labelProtostellar1 = new Label();
        labelProtostellar1.relocate(450, 310);
        labelProtostellar1.setText("Protostellar in structs: "+controller.round(
                (double)SingletonReq10.getInstance().getProtostellar_true()/
                        (double)(SingletonReq10.getInstance().getTotal_true())*100.0) + " %");
        root.getChildren().addAll(labelProtostellar1);


        columnId.setMinWidth(140);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        columnName.setMinWidth(140);
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        columnId.setSortable(false);
        columnName.setSortable(false);

        tableView.setPrefSize(300, 510);
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
                dbController.showStar(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    private void riempi() {
        if (getnTotalPages() != getnCurrentPage() || (getnTotalPages() == getnCurrentPage() && SingletonReq10.getInstance().getBeansToShow().size() %20 == 0)) {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + 20; i++) {
                parseBean(SingletonReq10.getInstance().getBeansToShow().get(i).getId(),
                        SingletonReq10.getInstance().getBeansToShow().get(i).getName());
            }
        }
        else {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + SingletonReq10.getInstance().getBeansToShow().size() %20; i++) {
                parseBean(SingletonReq10.getInstance().getBeansToShow().get(i).getId(),
                        SingletonReq10.getInstance().getBeansToShow().get(i).getName());
            }
        }
    }

    public void parseBean(Integer id, String name){
        Controller controller = new Controller();
        list.add(controller.createReq9_10Bean(id,name));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setItems(list);
    }
}
