package Control;

import Dao.UserDao;
import Entity.User;

public class UserController {

    /*
    TODO singleton
     */
    public UserController(){

    }

    public boolean login(User user) {
        return UserDao.daoLogin(user);
    }



}
