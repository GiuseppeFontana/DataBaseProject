package Utils;

public class Strings {



    public static String st_type1 = "UNBOUND";
    public static String st_type2 = "PRESTELLAR";
    public static String st_type3 = "PRESTELLAR";

    // query
    public static String strLogin = "SELECT * FROM users WHERE username = '%s' AND password = '%s';";
    public static String strRegister = "INSERT INTO users VALUES ('%s','%s','%s','%s','%s','%s')";

    public static String strReq51 = "SELECT * FROM %s_boundaries WHERE id = '%s'";
    
}
