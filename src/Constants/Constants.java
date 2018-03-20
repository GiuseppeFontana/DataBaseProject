package Constants;

public class Constants {

    public static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static String DB_USER = "postgres";
    public static String DB_PASS = "root";

    public static String st_type1 = "UNBOUND";
    public static String st_type2 = "PRESTELLAR";
    public static String st_type3 = "PRESTELLAR";

    // query
    public static String strLogin = "SELECT * FROM users WHERE username = '%s' AND password = '%s';";


}
