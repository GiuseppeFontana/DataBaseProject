package Prove;


import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LabelProva extends Application{
    public Label label = new Label("count: ");
    public int count=1;

    @Override
    public void start(Stage primaryStage) throws Exception {



        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Prove/label.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 686, 649);

        Button buttonPlus = new Button("click");
        buttonPlus.relocate(50, 200);

        label.relocate(50, 250);

        buttonPlus.setOnAction(event -> {
            count++;
            label.setText("Count: "+Integer.toString(count));
        });

        root.getChildren().add(buttonPlus);
        root.getChildren().add(label);


        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();


    }

}
