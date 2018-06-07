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

        // STEP 1: dichiarazioni
        PreparedStatement ps1 = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            //"DELETE FROM herschel_skeletons"
           /* String sqlDel = String.format(Strings.strDelete, instrument, table);
            System.out.println(sqlDel);
            ps1 = conn.prepareStatement(sqlDel);
            int resDel = ps1.executeUpdate();
            System.out.println("rows deleted: "+resDel);
*/
            //"COPY herschel_skeletons FROM '/home/giuseppe/Scrivania/basedati/modded_csv/scheletro_filamenti_Herschel.csv' DELIMITER ','"
            //String sql =String.format(Strings.strImport, instrument, table, /*instrument, table,*/ path);

            /*String sql1 = String.format(Strings.strImport1, instrument, table);
            System.out.println(sql1);
            ps1 = conn.prepareStatement(sql1);
            ps1.execute();*/

            /*String sql2 = String.format(Strings.strImport2, path);
            ps2 = conn.prepareStatement(sql2);
            int resImp = ps2.executeUpdate();
            System.out.println("Imported " + resImp + " rows into temporary table");

            String sql3 = String.format(Strings.strImport3, instrument, table);
            ps2 = conn.prepareStatement(sql3);
            resImp = ps2.executeUpdate();
            System.out.println("Imported " + resImp + " rows.");*/
            String sql = null;
            switch (table) {
                case "boundaries":
                    sql = String.format(Strings.strImport, instrument, table, instrument, table, path, instrument, table, instrument, table, Strings.strImportBound);
                    break;
                case "skeletons":
                    sql = String.format(Strings.strImport, instrument, table, instrument, table, path, instrument, table, instrument, table, Strings.strImportSkel);
                    break;
                case "stars":
                    sql = String.format(Strings.strImport, instrument, table, instrument, table, path, instrument, table, instrument, table, Strings.strImportStar);
                    break;
                case "structures":
                    sql = String.format(Strings.strImport, instrument, table, instrument, table, path, instrument, table, instrument, table, Strings.strImportStruct);
                    break;
                default:
                    break;
            }

            System.out.println(sql);
            ps1 = conn.prepareStatement(sql);
            ps1.execute();

            conn.commit();

            // STEP 6: Clean-up dell'ambiente
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
