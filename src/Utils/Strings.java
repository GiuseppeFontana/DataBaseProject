package Utils;

public class Strings {



    public static String st_type1 = "UNBOUND";
    public static String st_type2 = "PRESTELLAR";
    public static String st_type3 = "PRESTELLAR";

    // query
    public static String strLogin = "SELECT * FROM users WHERE username = '%s' AND password = '%s';";
    public static String strRegister = "INSERT INTO users VALUES ('%s','%s','%s','%s','%s','%s')";

    public static String strReq51 = "SELECT * FROM %s_boundaries WHERE id = '%s'";
    /*
    TODO stringa Alessia
    SELECT COUNT(DISTINCT idbranch) FROM herschel_skeletons WHERE idfil='x';
     */
    public static String strReq52 = "SELECT COUNT(DISTINCT idbranch) FROM %s_skeletons WHERE idfil = '%s'";

    public static String strDelete = "DELETE FROM %s_%s";
    public static String strImport = "COPY %s_%s FROM '%s' DELIMITER ','";
    
}
