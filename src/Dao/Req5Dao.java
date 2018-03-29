package Dao;

import Control.Controller;
import Entity.Bound;
import Entity.Structure;
import Utils.Credenziali;
import Utils.Strings;

import java.sql.*;
import java.util.ArrayList;


/*
TODO questo non risponde al requisito 5

REQ-FN-5	Recupero	informazioni	derivate	di	un	filamento
Un	utente	registrato	potrà	ricercare	un	filamento	per	id	o	designazione,	e	di	questo
visualizzare:
1) La	posizione	del	centroide	del	contorno.	Il	centroide	sarà	calcolato	come	media	delle
latitudini	e	delle	longitudini.
2) L’estensione	del	contorno.	L’estensione	sarà	calcolata	come	la	distanza	tra	il	minimo
massimo	delle	posizioni	longitudinali,	e	tra	il	minimo	e	massimo	delle	posizioni	latitudinali.
3) Il	numero	di	segmenti	relativi.
*/

public class Req5Dao {
    public static ArrayList<Bound> req51(String strumento, String input) {

        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ArrayList<Bound> bounds = new ArrayList<>();
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            //stmt = conn.createStatement();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql = String.format(Strings.strReq51, strumento, input);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.first()) {// rs empty
                System.out.println("Accesso Contorni fallito");
                return null;
            }

            Controller controller = new Controller();

            //-----------PRIMA ENTRY-----------//
            Bound bound1 = controller.createBound(rs.getInt("id"), rs.getFloat("lon"), rs.getFloat("lat"));
            bounds.add(bound1);

            //-----------ALTRE ENTRY-----------//
            while (rs.next()){
                Bound bound = controller.createBound(rs.getInt("id"), rs.getFloat("lon"), rs.getFloat("lat"));
                bounds.add(bound);
            }

            conn.commit();

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
        System.out.println("Accesso Contorni effettuato con successo");
        return bounds;
        }

    public static int req52(String instrument, String input) {
        return 0;
    }
}
