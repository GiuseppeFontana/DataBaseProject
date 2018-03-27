package Dao;

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
    public static ArrayList<Structure> req51(String type, String strumento, String input) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ArrayList<Structure> strutture = new ArrayList<>();
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            conn.setAutoCommit(false);


            // STEP 4: creazione ed esecuzione della query
            //stmt = conn.createStatement();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String arg = type;

            String sql = String.format(Strings.strReq51, arg, input);
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.first()) {// rs empty
                System.out.println("Accesso Strutture fallito");
                return null;
            }


            Structure struttura1 = new Structure();

            //-----------PRIMA ENTRY-----------//
            int id1 = rs.getInt("id");
            String name1 = rs.getString("name");
            Double flux1 = rs.getDouble("flux");
            Double meanDens1 = rs.getDouble("meandens");
            int meanTemp1 = rs.getInt("meantemp");
            int ellipt1 = rs.getInt("ellipt");
            int contrast1 = rs.getInt("contrast");
            String satellite1 = rs.getString("satellite");
            String instrument1 = rs.getString("instrument");

            struttura1.setId(id1);
            struttura1.setName(name1);
            struttura1.setFlux(flux1);
            struttura1.setMeanDens(meanDens1);
            struttura1.setMeanTemp(meanTemp1);
            struttura1.setEllipt(ellipt1);
            struttura1.setContrast(contrast1);
            struttura1.setSatellite(satellite1);
            struttura1.setInstrument(instrument1);

            strutture.add(struttura1);
            //-----------FINE PRIMA ENTRY-----------//

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double flux = rs.getDouble("flux");
                Double meanDens = rs.getDouble("meandens");
                int meanTemp = rs.getInt("meantemp");
                int ellipt = rs.getInt("ellipt");
                int contrast = rs.getInt("contrast");
                String satellite = rs.getString("satellite");
                String instrument = rs.getString("instrument");

                Structure structure = new Structure();

                structure.setId(id);
                structure.setName(name);
                structure.setFlux(flux);
                structure.setMeanDens(meanDens);
                structure.setMeanTemp(meanTemp);
                structure.setEllipt(ellipt);
                structure.setContrast(contrast);
                structure.setSatellite(satellite);
                structure.setInstrument(instrument);

                strutture.add(structure);
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
        System.out.println("Accesso Strutture effettuato con successo");
        return strutture;
    }
}
