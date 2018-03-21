package Control;

import Dao.UserDao;
import Entity.User;

public class DBController {

    private static DBController instance;

    private DBController(){

    }

    public static DBController getInstance(){
        if (instance == null)
            instance = new DBController();
        return instance;
    }

    public boolean login(User user) {
        return UserDao.daoLogin(user);
    }
}
