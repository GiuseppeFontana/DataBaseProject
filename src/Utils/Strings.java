package Utils;

public class Strings {



    /*public static String st_type1 = "UNBOUND";
    public static String st_type2 = "PRESTELLAR";
    public static String st_type3 = "PRESTELLAR";*/

    // query
    public static String strLogin = "SELECT * FROM users WHERE username = '%s' AND password = '%s';";
    public static String strRegister = "INSERT INTO users VALUES ('%s','%s','%s','%s','%s','%s')";
    public static String strInsertSatellite = "INSERT INTO satellite VALUES ('%s','%s','%s','%s')"; //inserimento nuovo satellite
    public static String strInsertInstrument = "INSERT INTO instrument VALUES ('%s','%s','%s')"; //inserimento nuovo strumento

    public static String strReq51 = "SELECT %s FROM %s_boundaries WHERE id = '%s'";
    public static String strReq52 = "SELECT COUNT(DISTINCT idbranch) FROM %s_skeletons WHERE idfil = '%s'";

    public static String strReq61 = "SELECT id, name, satellite FROM herschel_structures WHERE (contrast > %s AND ellipt >= %s AND ellipt <= %s)\n"
            + "UNION SELECT id, name, satellite FROM spitzer_structures WHERE (contrast > %s AND ellipt >= %s AND ellipt <= %s)";
    public static String strReq62 = "SELECT count(*) FROM %s_structures";

    public static String strReq7 = "(SELECT skeleton.idfil, structure.name, count(DISTINCT skeleton.idbranch)\n" +
            "FROM (%s_skeletons AS skeleton JOIN %s_structures AS structure ON skeleton.idfil = structure.id)\n" +
            "GROUP BY skeleton.idfil, structure.name\n" +
            "HAVING (count(DISTINCT skeleton.idbranch)>= %s AND count(DISTINCT skeleton.idbranch)<= %s))\n"+
            "UNION "+"(SELECT skeleton.idfil, structure.name, count(DISTINCT skeleton.idbranch)\n" +
            "FROM (%s_skeletons AS skeleton JOIN %s_structures AS structure ON skeleton.idfil = structure.id)\n" +
            "GROUP BY skeleton.idfil, structure.name\n" +
            "HAVING (count(DISTINCT skeleton.idbranch)>= %s AND count(DISTINCT skeleton.idbranch)<= %s))";

    public static String strDelete = "DELETE FROM %s_%s";
    public static String strImport = "COPY %s_%s FROM '%s' DELIMITER ','";


}
