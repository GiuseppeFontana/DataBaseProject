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
        //Application.launch(GraphicController.class);

        ImportDao dao = new ImportDao();
        dao.importa("boundaries", "herschel", "/home/giuseppe/Scrivania/basedati/modded_csv/contorni_filamenti_Herschel.csv");

        /*String ciao = "ciao123456789";
        System.out.println(ciao);
        String a2 = ciao.substring(4, ciao.length()-1);
        System.out.println(a2);*/
    }
}
