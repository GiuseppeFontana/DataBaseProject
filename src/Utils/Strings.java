package Utils;

public class Strings {



    public static String st_type1 = "UNBOUND";
    public static String st_type2 = "PRESTELLAR";
    public static String st_type3 = "PRESTELLAR";

    // query
    public static String strLogin = "SELECT * FROM users WHERE username = '%s' AND password = '%s';";
    public static String strRegister = "INSERT INTO users VALUES ('%s','%s','%s','%s','%s','%s')";

    public static String strReq51 = "SELECT %s FROM %s_boundaries WHERE id = '%s'";
    public static String strReq52 = "SELECT COUNT(DISTINCT idbranch) FROM %s_skeletons WHERE idfil = '%s'";
    public static String strReq61 = "SELECT * FROM %s_structures WHERE (contrast > %s AND ellipt >= %s AND ellipt <= %s)";
    public static String strReq62 = "SELECT count(*) FROM %s_structures";
    /*
    TODO sistemare la query
     */
    public static String strReq71 = "SELECT idfil, name, count(DISTINCT idbranch) FROM %s_skeletons JOIN %s_structures WHERE idfil=id GROUP BY idfil HAVING (count(DISTINCT idbranch)>= %s AND count(DISTINCT idbranch)<= %s";

    public static String strDelete = "DELETE FROM %s_%s";
    public static String strImport = "COPY %s_%s FROM '%s' DELIMITER ','";
    
}
