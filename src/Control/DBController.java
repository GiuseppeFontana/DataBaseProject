package Control;

import Dao.UserDao;
import Entity.User;

public class DBController {

    public DBController(){

    }


    public boolean login(User user) {
        return UserDao.daoLogin(user);
    }
}
