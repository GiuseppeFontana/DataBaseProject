package Dao;

import Entity.User;
import Constants.Constants;

import java.sql.*;

public class UserDao {
    public static boolean daoLogin(User user){
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASS);
            conn.setAutoCommit(false);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String sql = String.format(Constants.strLogin, user.getUsername(), user.getPassword());

                    /*"SELECT * FROM utenti where username = '" + user.getUsername()
                    + "' AND password = '" + user.getPassword() + "';";*/
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs empty
                return false;

            /*
            boolean moreThanOne = rs.first() && rs.next();
            assert !moreThanOne; // per abilitare le asserzioni, avviare la JVM con il parametro -ea
            // (Run Configurations -> <configurazione utilizzata per l'avvio del server> -> Arguments -> VM Arguments).
            // N.B. Le asserzioni andrebbero usate solo per test e debug, non per codice in produzione


            // riposizionamento del cursore
            rs.first();
            */

            // lettura delle colonne "by name"


            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setAdmin(rs.getBoolean("admin"));



            //assert (usernameLoaded.equals(username));


            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();

            conn.commit();
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
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return true;
    }
}