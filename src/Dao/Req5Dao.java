package Dao;

import Bean.Req5Bean;
import Control.Controller;
import Entity.Bound;
import Entity.Structure;
import Singletons.SingletonReq5;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;

public class Req5Dao {
    public static boolean req5(String strumento, int input) {

        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;

        Double infoFilamento[] = new Double[6];
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String k[] = new String[7];
            k[0] = "AVG(lon)";
            k[1] = "AVG(lat)";
            k[2] = "MIN(lon)";
            k[3] = "MIN(lat)";
            k[4] = "MAX(lon)";
            k[5] = "MAX(lat)";
            k[6] = String.format(Strings.strReq52, strumento, Integer.toString(input));

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

                infoFilamento[i] = resultSets[i].getDouble(1);
                System.out.println(k[i] + ": " + infoFilamento[i]);
            }

            System.out.println(k[6]);
            resultSets[6] = stmt.executeQuery(k[6]);
            if (!resultSets[6].first()) {// rs empty
                System.out.println("Accesso Scheletri fallito");
                return false;
            }

            System.out.println("count: " +resultSets[6].getInt("count"));

            SingletonReq5.getInstance().setBean(new Req5Bean());
            SingletonReq5.getInstance().getBean().setnSegmenti(resultSets[6].getInt("count"));

            if (infoFilamento[2] == infoFilamento[4] || infoFilamento[3] == infoFilamento[5] || SingletonReq5.getInstance().getBean().getnSegmenti() == 0) {
                System.out.println("errore imprevisto accesso db, risultati nulli (filamento non presente?");
                return false;
            }

            SingletonReq5.getInstance().getBean().setId(input);
            SingletonReq5.getInstance().getBean().setLonCenter(infoFilamento[0]);
            SingletonReq5.getInstance().getBean().setLatCenter(infoFilamento[1]);
            SingletonReq5.getInstance().getBean().setLonExtension(infoFilamento[4]-infoFilamento[2]);
            SingletonReq5.getInstance().getBean().setLatExtension(infoFilamento[5]-infoFilamento[3]);

            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            for (int i = 0; i < 7; i++) {
                resultSets[i].close();
            }
            stmt.close();
            conn.close();

            System.out.println("Accesso Contorni e Scheletri effettuato con successo");
            return true;
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
        System.out.println("Accesso Contorni e Scheletri fallito");
        return false;
    }
}
