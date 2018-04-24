package Boundary.Requisito_07;

import Bean.Req7Bean;
import Control.Controller;
import Control.GraphicController;
import Singletons.SingletonReq7;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_7_Result {

    public Button ButtonNext;
    public Button ButtonPrev;
    public Button BackButton;

    @FXML
    private Label labelResult;
    @FXML
    private static int nCurrentPage;
    @FXML
    private static int nTotalPages;

    @FXML
    private javafx.scene.control.TableView<Req7Bean> tableView = new TableView<>();
    @FXML
    private TableColumn<Req7Bean, Integer> columnId = new TableColumn<>("id");
    @FXML
    private TableColumn<Req7Bean, String> columnName = new TableColumn<>("name");
    @FXML
    private TableColumn<Req7Bean, String> columnSatellite = new TableColumn<>("satellite");
    @FXML
    private TableColumn<Req7Bean, Integer> columnNumeroSegmenti = new TableColumn<>("n. segmenti");
    @FXML
    private static ObservableList<Req7Bean> list = FXCollections.observableArrayList();


    public static int getnCurrentPage() {
        return nCurrentPage;
    }

    public static void setnCurrentPage(int n) {
        nCurrentPage = n;
    }

    public static int getnTotalPages() {
        return nTotalPages;
    }

    public static void setnTotalPages(int n) {
        nTotalPages = n;
    }

    public void start() throws Exception {
        setnCurrentPage(1);
        int size = SingletonReq7.getInstance().getBeans().size();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_7_Result.class.getResource("req_7_result.fxml"));        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );

        labelResult = new Label();
        labelResult.setText("Trovate "+ SingletonReq7.getInstance().getStruttureTrovate() +" strutture.");
        labelResult.setLayoutX(220);
        labelResult.setLayoutY(50);
        root.getChildren().addAll(labelResult);

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

        columnNumeroSegmenti.setMinWidth(140);
        columnNumeroSegmenti.setCellValueFactory(new PropertyValueFactory<>("count"));

        tableView.setPrefSize(537, 510);
        tableView.setLayoutX(100);
        tableView.setLayoutY(65);

        ((AnchorPane) scene.getRoot()).getChildren().addAll(tableView);
        tableView.setItems(list);
        tableView.getColumns().addAll(columnId,columnName, columnSatellite, columnNumeroSegmenti);

        riempi();


        /*
        //TODO implementare la view come nel requisito 6
        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            String satellite = tableView.getSelectionModel().getSelectedItem().getSatellite();
            int id = tableView.getSelectionModel().getSelectedItem().getId();
            System.out.println("id:"+Integer.toString(id)+";\t satellite: "+satellite);
        }));*/

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
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

    private void riempi() {
        if (getnTotalPages() != getnCurrentPage() || (getnTotalPages() == getnCurrentPage() && SingletonReq7.getInstance().getBeans().size() %20 == 0)) {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + 20; i++) {
                parseBean(SingletonReq7.getInstance().getBeans().get(i).getIdStructure(),
                        SingletonReq7.getInstance().getBeans().get(i).getNameStructure(),
                        SingletonReq7.getInstance().getBeans().get(i).getSatellite(),
                        SingletonReq7.getInstance().getBeans().get(i).getnSegmenti());
            }
        }
        else {
            for (int i = (getnCurrentPage() - 1) * 20; i < 20 * (getnCurrentPage() - 1) + SingletonReq7.getInstance().getBeans().size() %20; i++) {
                parseBean(SingletonReq7.getInstance().getBeans().get(i).getIdStructure(),
                        SingletonReq7.getInstance().getBeans().get(i).getNameStructure(),
                        SingletonReq7.getInstance().getBeans().get(i).getSatellite(),
                        SingletonReq7.getInstance().getBeans().get(i).getnSegmenti());
            }
        }
        System.out.println("Pagina " + getnCurrentPage() + " di " + getnTotalPages());
    }

    public void backHome(ActionEvent actionEvent) throws Exception{
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        controller.resetSingleton7();
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

    public void parseBean(Integer id, String name, String satellite, Integer n){
        Controller controller = new Controller();
        list.add(controller.createReq7Bean(id,name,satellite, n));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSatellite.setCellValueFactory(new PropertyValueFactory<>("satellite"));
        columnNumeroSegmenti.setCellValueFactory(new PropertyValueFactory<>("count"));
        tableView.setItems(list);
    }
}
