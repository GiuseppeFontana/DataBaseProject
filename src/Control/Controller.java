package Control;

import Entity.User;
import Utils.ClassicSingleton;

public class Controller {

    public void createUser(String username, String password, String email, String name, String surname, Boolean admin){

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAdmin(admin);
        user.setPassword(password);
        user.setUsername(username);
        user.setSurname(surname);

        //--------CREAZIONE DEL SINGLETON-----------//

        ClassicSingleton singleton = ClassicSingleton.getInstance();
        singleton.setUser(user);

    }


}
