package Dao;

import Control.Controller;
import Utils.Credenziali;
import Utils.Strings;
import Control.GraphicController;
import Entity.User;
import java.sql.*;

public class UserDao {
    public static boolean daoLogin(String u, String p){
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);


            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String sql = String.format(Strings.strLogin, u, p);
            System.out.println("query:\n"+sql);

            /*
            se non metto questo la seguente riga mi da l'errore:
            org.postgresql.util.PSQLException: L'operazione richiete un «ResultSet» scorribile mentre questo è «FORWARD_ONLY».
             */
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);

            // rs empty
            if (!rs.first()){
                System.out.println("empty resultset;");
                GraphicController graphicController = new GraphicController();
                graphicController.incorrect();
                return false;
            }


            /*
            boolean moreThanOne = rs.first() && rs.next();
            assert !moreThanOne;
            // per abilitare le asserzioni, avviare la JVM con il parametro -ea
            // (Run Configurations -> <configurazione utilizzata per l'avvio del server> -> Arguments -> VM Arguments).
            // N.B. Le asserzioni andrebbero usate solo per test e debug, non per codice in produzione


            // riposizionamento del cursore
            rs.first();
            */

            // lettura delle colonne "by name"


            /*user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setAdmin(rs.getBoolean("admin"));*/

            /*String username = rs.getString("username");
            String password = rs.getString("password");*/
            String name = rs.getString("name");
            String email = rs.getString("email");
            String surname = rs.getString("surname");
            Boolean admin = rs.getBoolean("admin");

            Controller controller = new Controller();
            controller.createUser(u, p, email, name, surname, admin);



            //assert (usernameLoaded.equals(username));


            // STEP 6: Clean-up dell'ambiente
            rs.close();
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
        return true;
    }
}