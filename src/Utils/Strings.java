package Utils;

public class Strings {

    // show
    public static String strShowStruct = "SELECT * FROM %s_structures WHERE id = '%s'";


    // admin query
    public static String strLogin = "SELECT * FROM users WHERE username = '%s' AND password = '%s';";
    public static String strRegister = "INSERT INTO users VALUES ('%s','%s','%s','%s','%s','%s')";
    public static String strInsertSatellite = "INSERT INTO satellite VALUES ('%s','%s','%s','%s')"; //inserimento nuovo satellite
    public static String strInsertInstrument = "INSERT INTO instrument VALUES ('%s','%s','%s')"; //inserimento nuovo strumento

    // import
    public static String strDelete = "DELETE FROM %s_%s";
    public static String strImport = "COPY %s_%s FROM '%s' DELIMITER ','";



    // requisiti
    public static String strReq51 = "SELECT %s FROM %s_boundaries WHERE id = '%s'";
    public static String strReq52 = "SELECT COUNT(DISTINCT idbranch) FROM %s_skeletons WHERE idfil = '%s'";

    public static String strReq61 = "SELECT id, name, satellite FROM herschel_structures WHERE (contrast > %s AND ellipt >= %s AND ellipt <= %s)\n"
            + "UNION SELECT id, name, satellite FROM spitzer_structures WHERE (contrast > %s AND ellipt >= %s AND ellipt <= %s)";
    public static String strReq62 = "SELECT count(*) FROM %s_structures";


    public static String strReq7 = "(SELECT skeleton.idfil, structure.name, structure.satellite, count(DISTINCT skeleton.idbranch)\n" +
            "FROM (%s_skeletons AS skeleton JOIN %s_structures AS structure ON skeleton.idfil = structure.id)\n" +
            "GROUP BY skeleton.idfil, structure.name, structure.satellite\n" +
            "HAVING (count(DISTINCT skeleton.idbranch)>= %s AND count(DISTINCT skeleton.idbranch)<= %s))\n"+
            "UNION "+"(SELECT skeleton.idfil, structure.name, structure.satellite, count(DISTINCT skeleton.idbranch)\n" +
            "FROM (%s_skeletons AS skeleton JOIN %s_structures AS structure ON skeleton.idfil = structure.id)\n" +
            "GROUP BY skeleton.idfil, structure.name, structure.satellite\n" +
            "HAVING (count(DISTINCT skeleton.idbranch)>= %s AND count(DISTINCT skeleton.idbranch)<= %s))";



    public static String strReq81 = "(SELECT s.id, s.name, s.satellite FROM herschel_structures s EXCEPT " +
            "(SELECT s2.id, s2.name, s2.satellite FROM herschel_structures AS s2 JOIN herschel_boundaries AS b ON s2.id = b.id " +
            "WHERE (b.lon < %s OR b.lon > %s OR b.lat < %s OR b.lat > %s))) " +
            "UNION (SELECT s.id, s.name, s.satellite FROM spitzer_structures s EXCEPT " +
            "(SELECT s2.id, s2.name, s2.satellite FROM spitzer_structures AS s2 JOIN spitzer_boundaries AS b ON s2.id = b.id " +
            "WHERE (b.lon < %s OR b.lon > %s OR b.lat < %s OR b.lat > %s)))";

    public static String strReq82 = "(SELECT s.id, s.name, s.satellite, b.lon, b.lat FROM herschel_structures AS s " +
            "JOIN herschel_boundaries AS b ON s.id = b.id EXCEPT " +
            "(SELECT s2.id, s2.name, s2.satellite, b2.lon, b2.lat FROM herschel_structures AS s2 " +
            "JOIN herschel_boundaries AS b2 ON s2.id = b2.id WHERE (b2.lon < %s OR b2.lon > %s OR b2.lat < %s OR b2.lat > %s))) " +
            "UNION (SELECT s.id, s.name, s.satellite, b.lon, b.lat FROM spitzer_structures AS s " +
            "JOIN spitzer_boundaries AS b ON s.id = b.id EXCEPT (SELECT s2.id, s2.name, s2.satellite, b2.lon, b2.lat " +
            "FROM spitzer_structures AS s2 JOIN spitzer_boundaries AS b2 ON s2.id = b2.id " +
            "WHERE (b2.lon < %s OR b2.lon > %s OR b2.lat < %s OR b2.lat > %s)))";
}
