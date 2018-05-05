package Boundary.Requisito_08;

import Bean.Req6_8SquareBean;
import Control.Controller;
import Control.DBController;
import Control.GraphicController;
import Singletons.SingletonReq8;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_8_Result {
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

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_8_Result.class.getResource("req_8_result.fxml"));        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

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

        columnSatellite.setMinWidth(140);
        columnSatellite.setCellValueFactory(new PropertyValueFactory<>("satellite"));

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
        if (getnTotalPages() != getnCurrentPage() || (getnTotalPages() == getnCurrentPage() && SingletonReq8.getInstance().getBeans().size() %20 == 0)) {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + 20; i++) {
                parseBean(SingletonReq8.getInstance().getBeans().get(i).getId(),
                        SingletonReq8.getInstance().getBeans().get(i).getName(),
                        SingletonReq8.getInstance().getBeans().get(i).getSatellite());
            }
        }
        else {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + SingletonReq8.getInstance().getBeans().size() %20; i++) {
                parseBean(SingletonReq8.getInstance().getBeans().get(i).getId(),
                        SingletonReq8.getInstance().getBeans().get(i).getName(),
                        SingletonReq8.getInstance().getBeans().get(i).getSatellite());
            }
        }
        System.out.println("Pagina " + getnCurrentPage() + " di " + getnTotalPages());
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
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        controller.resetSingleton8();
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
}
