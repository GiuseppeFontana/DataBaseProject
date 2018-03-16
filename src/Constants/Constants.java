package Constants;

public class Constants {
    public static String strLogin = "SELECT * FROM utenti where username = '%s' AND password = '%s';";

    /*TODO inserisci dati db*/
    public static String DB_URL = "jdbc:posgresql://localhost:5432/dbserver";
    public static String DB_USER = "postgres";
    public static String DB_PASS = "postgres";

    /*
    String DB_USER = "root";
    String DB_PASS = "1234";
    String DB_URL  = "jdbc:mysql://localhost:3306/db1";
     */
}
