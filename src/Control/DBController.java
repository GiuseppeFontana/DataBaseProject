package Control;

import Dao.RegisterDao;
import Dao.Req5Dao;
import Dao.UserDao;
import Entity.Bound;
import Entity.SkeletonPoint;
import Entity.Structure;

import java.util.ArrayList;

public class DBController {

    public DBController(){

    }

    public boolean login(String username, String password) {
        return UserDao.daoLogin(username, password);
    }

    public boolean registraUtente(String name, String surname, String username, String pass1, String email, boolean type) {
        return RegisterDao.req32(name, surname, username, pass1, email, type);
    }


    public boolean infoDerivateFilamento(String instrument, String input) throws Exception {

        ArrayList<Bound> bounds = Req5Dao.req51(instrument, input);
        int nBranches = Req5Dao.req52(instrument, input);
        if (/*bounds == null|| */nBranches == 0){
            return false;
        }
        else {  //calcola centroide, max e min coordinate e crea l'interfaccia

            int size = bounds.size();
            double longitude;
            double latitude;

            double infoContorno[] = new double[9];

            infoContorno[0] = 0.0;     //long centroide
            infoContorno[1] = 0.0;     //lat centroide

            infoContorno[2] = 0.0;     //long min
            infoContorno[3] = 0.0;      //long max

            infoContorno[4] = 0.0;      //lat min
            infoContorno[5] = 0.0;      //lat max

            infoContorno[6] = 0.0;      //estensione longitudinale
            infoContorno[7] = 0.0;      //estensione latitudinale

            infoContorno[8] = (double) nBranches;      // n segmenti

            for (int k=0; k < size; k++){
                longitude = bounds.get(k).getLongitude();
                latitude = bounds.get(k).getLatitude();

                infoContorno[0] += longitude;
                infoContorno[1] += latitude;

                if (longitude<infoContorno[2])
                    infoContorno[2] = longitude;
                if (longitude>infoContorno[3])
                    infoContorno[3] = longitude;
                if (latitude<infoContorno[4])
                    infoContorno[4] = latitude;
                if (latitude>infoContorno[5])
                    infoContorno[5] = latitude;
            }

            infoContorno[0] = infoContorno[0]/((double)size);
            infoContorno[1] = infoContorno[1]/((double)size);
            infoContorno[6] = infoContorno[3] - infoContorno[2];
            infoContorno[7] = infoContorno[5] - infoContorno[4];

            GraphicController graphicController = new GraphicController();
            graphicController.req5result(input, infoContorno);
            return  true;
        }
    }
}
