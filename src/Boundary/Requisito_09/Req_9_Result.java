package Boundary.Requisito_09;

import Bean.Req9_10Bean;
import Control.Controller;
import Control.DBController;
import Singletons.SingletonReq9;
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

public class Req_9_Result {
    @FXML
    private Button BackButton;
    @FXML
    private Button ButtonNext;
    @FXML
    private Button ButtonPrev;

    @FXML
    private static int nCurrentPage;
    @FXML
    private static int nTotalPages;

    @FXML
    private Label labelTotal;
    @FXML
    private Label labelUnbound;
    @FXML
    private Label labelPrestellar;
    @FXML
    private Label labelProtostellar;


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

    public void start() throws Exception{

        Controller controller = new Controller();
        controller.calcolaTipi9();

        System.out.println("unbound: " + SingletonReq9.getInstance().getUnbound() +
                "\nprestellar: " + SingletonReq9.getInstance().getPrestellar() +
                "\nprotostellar: " + SingletonReq9.getInstance().getProtostellar());

        setnCurrentPage(1);
        int size = SingletonReq9.getInstance().getBeans().size();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_9_Result.class.getResource("req_9_result.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        labelTotal = new Label();
        labelTotal.relocate(450, 100);
        labelTotal.setText("Stelle trovate: "+ SingletonReq9.getInstance().getBeans().size());
        root.getChildren().add(labelTotal);

        labelUnbound = new Label();
        labelUnbound.relocate(450, 150);
        labelUnbound.setText("Unbound: "+ controller.round((double)SingletonReq9.getInstance().getUnbound()/(double)(SingletonReq9.getInstance().getBeans().size())*100.0) + " %");
        root.getChildren().addAll(labelUnbound);

        labelPrestellar = new Label();
        labelPrestellar.relocate(450, 170);
        labelPrestellar.setText("Prestellar: "+ controller.round((double)SingletonReq9.getInstance().getPrestellar()/(double)SingletonReq9.getInstance().getBeans().size()*100.0) + " %");
        root.getChildren().addAll(labelPrestellar);

        labelProtostellar = new Label();
        labelProtostellar.relocate(450, 190);
        labelProtostellar.setText("Protostellar: "+ controller.round((double)SingletonReq9.getInstance().getProtostellar()/(double)SingletonReq9.getInstance().getBeans().size()*100.0)+" %");
        root.getChildren().addAll(labelProtostellar);

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
        list.add(controller.createReq9_10Bean(id,name));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setItems(list);
    }

    public void backHome(ActionEvent actionEvent) throws Exception{
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        controller.resetSingleton9();
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
}
