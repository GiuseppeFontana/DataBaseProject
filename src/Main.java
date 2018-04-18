import Control.GraphicController;
import Dao.ImportDao;
import Dao.Req5Dao;
import Dao.Req6Dao;
import Entity.Structure;
import Prove.LabelProva;
import javafx.application.Application;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]){
        Application.launch(GraphicController.class);

       // Application.launch(LabelProva.class);

        /* TODO finire gli import
        ImportDao dao = new ImportDao();
        dao.importa("skeletons", "spitzer", "/home/giuseppe/Scrivania/basedati/modded_csv/scheletro_filamenti_Spitzer.csv");
        */
    }
}
