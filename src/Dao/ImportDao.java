package Dao;

import Control.GraphicController;
import Utils.Credenziali;
import Utils.Strings;
import org.postgresql.util.PSQLException;

import java.sql.*;

public class ImportDao {
    public ImportDao(){

    }

    public static void importa(String table, String instrument, String path){

        PreparedStatement ps1 = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            String sql = null;
            switch (table) {
                case "boundaries":
                    sql = String.format(Strings.strImport, instrument, table, instrument, table,
                            path, instrument, table, instrument, table, Strings.strImportBound);
                    break;
                case "skeletons":
                    sql = String.format(Strings.strImport, instrument, table, instrument, table,
                            path, instrument, table, instrument, table, Strings.strImportSkel);
                    break;
                case "stars":
                    sql = String.format(Strings.strImport, instrument, table, instrument, table,
                            path, instrument, table, instrument, table, Strings.strImportStar);
                    break;
                case "structures":
                    sql = String.format(Strings.strImport, instrument, table, instrument, table,
                            path, instrument, table, instrument, table, Strings.strImportStruct);
                    break;
                default:
                    break;
            }

            System.out.println(sql);
            ps1 = conn.prepareStatement(sql);
            ps1.execute();

            conn.commit();

            ps1.close();
            conn.close();
            GraphicController graphicController = new GraphicController();
            graphicController.alertError("Import effettuato\ncon successo.");

        } catch (Exception e) {
            e.printStackTrace();
            try{
                System.out.println("failed. eseguendo il rollback...");
                conn.rollback();
                GraphicController gc = new GraphicController();
                gc.alertError("Import fallito.");
            }catch (Exception e2){
                e2.printStackTrace();
            }
        } finally {
            try {
                if (ps1 != null)
                    ps1.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
