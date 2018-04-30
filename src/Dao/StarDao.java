package Dao;

import Control.Controller;
import Singletons.SingletonStar;
import Singletons.SingletonStruct;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;

public class StarDao {
    public static boolean searchStar(int id) {
        // STEP 1: dichiarazioni
        Statement stmt1 = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);

            // STEP 4: creazione ed esecuzione della query
            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql1 = String.format(Strings.strShowStar, Integer.toString(id));
            System.out.println(sql1);

            ResultSet rs = stmt1.executeQuery(sql1);

            if (!rs.first()){
                System.out.println("Resultset vuoto.");
                return false;
            }
            else {
                Controller controller = new Controller();
                SingletonStar.getInstance().setStar(controller.createStar(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("lon"),
                        rs.getDouble("lat"),
                        rs.getDouble("flux"),
                        rs.getString("type")));
            }

            conn.commit();

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt1.close();
            conn.close();
            return true;

        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt1 != null)
                    stmt1.close();
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
        System.out.println("Accesso Stelle fallito.");
        return false;
    }
}
