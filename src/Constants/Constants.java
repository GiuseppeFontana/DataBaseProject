package Constants;

public class Constants {
    public static String strLogin = "SELECT * FROM users WHERE username = '%s' AND password = '%s';";


    //public static String DB_URL = "jdbc:posgresql://localhost:5432/dbserver";
    public static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static String DB_USER = "postgres";
    public static String DB_PASS = "postgres";


}
