package Dao;

import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;

public class ImportDao {
    public ImportDao(){

    }

    public static void importa(String table, String instrument, String path){

        // STEP 1: dichiarazioni
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            //"DELETE FROM herschel_skeletons"
            String sql1 = String.format(Strings.strDelete, instrument, table);
            System.out.println(sql1);
            ps1 = conn.prepareStatement(sql1);
            int resDel = ps1.executeUpdate();
            System.out.println("rows deleted: "+resDel);

            //"COPY herschel_skeletons FROM '/home/giuseppe/Scrivania/basedati/modded_csv/scheletro_filamenti_Herschel.csv' DELIMITER ','"
            String sql2 =String.format(Strings.strImport, instrument, table, path);
            System.out.println(sql2);
            ps2 = conn.prepareStatement(sql2);
            int resImp = ps2.executeUpdate();
            System.out.println("Imported " + resImp + " rows...");


            //TODO se non va a buon fine fai rollback
            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            ps1.close();
            ps2.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (ps1 != null)
                    ps1.close();
                if (ps2 != null)
                    ps2.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
