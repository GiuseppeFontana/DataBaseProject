import Control.GraphicController;
import Dao.ImportDao;
import Dao.Req5Dao;
import Dao.Req6Dao;
import Entity.Structure;
import javafx.application.Application;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]){

        /*double info[] = new double[6];
        int n = 0;
        Req5Dao.req5("herschel", "460", info, n);

        Req6Dao req6Dao = new Req6Dao();
        ArrayList<Structure> prova = new ArrayList<>();
        if(req6Dao.req6(prova,"herschel", 115, 3.1, 7.5)){
            System.out.println(prova.size());
        }*/


        Application.launch(GraphicController.class);
    }
}
