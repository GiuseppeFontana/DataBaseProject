package Entity;

import Control.UserController;

public class User {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Boolean admin;          //1 se admin, 0 utente generico

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public boolean validate() {
        // Controllo sintattico
        if (this.username == null || this.password == null) {
            return false;
        }

        UserController controller = new UserController();
        return controller.login(this);
    }
}
