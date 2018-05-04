package Boundary.Requisito_03;

import Control.Controller;
import Control.GraphicController;
import Entity.User;
import Singletons.SingletonUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Req_3_Page {
    

    public void start() throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Req_3_Page.class.getResource("req_3_page.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("../Boundary/Requisito_03/req_3_page.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649);

        User user = SingletonUser.getInstance().getUser();
        String name = user.getName();

        final Label HomeLabel1 = new Label();

        HomeLabel1.setText("Benvenuto " + name);
        HomeLabel1.setLayoutX(320);
        HomeLabel1.setLayoutY(42);
        root.getChildren().addAll(HomeLabel1);

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

    public void jumpReq32Page(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.req32Page();
    }

    public void jumpReq33Page(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.req33Page();
    }

    public void jumpReq34Page(ActionEvent actionEvent) throws Exception {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.req34Page();
    }

    public void insertStrip(ActionEvent actionEvent) throws Exception{
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        graphicController.InserisciBanda();
    }
}


