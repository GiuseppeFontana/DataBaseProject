package Dao;

import Entity.Structure;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;

public class Req6Dao {
    public static ArrayList<Structure> req6(String satellite, double percBrillanza, double elliptMin, double elliptMax) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            double contrast = (percBrillanza/100) + 1.0;

            String sql = String.format(Strings.strReq6, satellite, contrast, elliptMin, elliptMax);
            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()){
                return null;
            }
            else {
                ArrayList<Structure> structures = new ArrayList<>();
                /*
                TODO finire
                 */
            }


            /*k[6] = String.format(Strings.strReq52, strumento, input);

            String sql;
            ResultSet resultSets[] = new ResultSet[7];

            for (int i = 0; i < 6; i++) {
                sql = String.format(Strings.strReq51, k[i], strumento, input);
                System.out.println(sql);
                resultSets[i] = stmt.executeQuery(sql);

                if (!resultSets[i].first()) {// rs empty
                    System.out.println("Accesso Contorni fallito");
                    return false;
                }

                infoFilamento[i] = resultSets[i].getFloat(1);
                System.out.println(k[i] + ": " + infoFilamento[i]);
            }

            System.out.println(k[6]);
            resultSets[6] = stmt.executeQuery(k[6]);
            if (!resultSets[6].first()) {// rs empty
                System.out.println("Accesso Scheletri fallito");
                return false;
            }

            nSegmenti[0] = resultSets[6].getInt("count");
            System.out.println("nSegmenti: " + nSegmenti[0]);

            if (infoFilamento[2] == infoFilamento[4] || infoFilamento[3] == infoFilamento[5] || nSegmenti[0] == 0) {
                System.out.println("errore imprevisto accesso db, risultati nulli (filamento non presente?");
                return false;
            }

            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            for (int i = 0; i < 7; i++) {
                resultSets[i].close();
            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Accesso Contorni e Scheletri effettuato con successo");
        return true;*/
    }
}
