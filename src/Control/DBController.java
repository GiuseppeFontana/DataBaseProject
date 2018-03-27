package Control;

import Dao.RegisterDao;
import Dao.Req5Dao;
import Dao.UserDao;
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


    public boolean ricercaFilamentoPerIdONome(String type, String instrument, String input) {
        ArrayList<Structure> structures = Req5Dao.req51(type,instrument, input);
        if (structures == null){
            return false;
        }
        else {
            for (int i = 0; i<structures.size(); i++ ){
                System.out.println(structures.get(i).getInstrument());
            }
        }
        return  true;
    }
}
