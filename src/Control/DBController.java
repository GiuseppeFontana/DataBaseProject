package Control;

import Dao.UserDao;
import Entity.User;

public class DBController {

    public DBController(){

    }

    public boolean login(String username, String password) {
        return UserDao.daoLogin(username, password);
    }
}
