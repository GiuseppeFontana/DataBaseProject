package Dao;

import Bean.Req7Bean;
import Control.Controller;
import Entity.Structure;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;

public class Req7Dao {
    public static boolean req7(ArrayList<Req7Bean> beans, String satellite, int min, int max) throws Exception {
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

            String sql = String.format(Strings.strReq7, satellite, satellite, Integer.toString(min), Integer.toString(max));
            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()) {
                System.out.println("Resultset vuoto.");
                return false;
            } else {
                Controller controller = new Controller();
                Req7Bean bean1 = controller.createReq7Bean(rs.getInt("idfil"), rs.getString("name"), rs.getInt("count"));
                beans.add(bean1);

                while (rs.next()) {
                    Req7Bean bean = controller.createReq7Bean(rs.getInt("idfil"), rs.getString("name"), rs.getInt("count"));
                    beans.add(bean);
                }
            }
            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
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
        System.out.println("Accesso DB effettuato con successo");
        return true;
    }
}
