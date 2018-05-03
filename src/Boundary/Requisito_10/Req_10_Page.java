package Boundary.Requisito_10;
import Control.Controller;
import Control.GraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_10_Page {
    @FXML
    private Button Req10SearchButton;
    @FXML
    private Button Req10BackButton;
    @FXML
    private TextField LatText;
    @FXML
    private TextField LonText;
    @FXML
    private TextField CenterLonText;
    @FXML
    private TextField CenterLatText;


    public void start() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_10_Page.class.getResource("req_10_page.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649 );
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }



    public void backHome(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        Controller controller = new Controller();
        boolean admin = controller.getUserSingleton().getUser().getAdmin();
        if (!admin) {
            GraphicController graphicController = new GraphicController();
            graphicController.homeUser();

        }
        if (admin) {
            GraphicController graphicController = new GraphicController();
            graphicController.homeAdmin();
        }
    }

    public void search(ActionEvent actionEvent)  {
    }
}