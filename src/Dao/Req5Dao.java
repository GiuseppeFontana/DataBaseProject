package Dao;

import Entity.Structure;
import Utils.Credenziali;

import java.sql.*;
import java.util.ArrayList;

public class Req5Dao {
    public static boolean req5(boolean type, String input) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ArrayList<Structure> strutture = new ArrayList<>();
        try {
            // STEP 2: loading dinamico del driver
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(Credenziali.G_DB_URL, Credenziali.G_DB_USER, Credenziali.G_DB_PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM prodotti where venditore = '"
                    + vendor + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.first()) {// rs empty
                System.out.println("Accesso Prodotti fallito");
                return null;
            }


            Prodotto prodotto;
            FactoryProdotti factoryProdotti = FactoryProdotti.getIstanza();
            String scollo = null;
            String chiusura = null;
            String puntale = null;
            String vestibilità = null;

            //-----------PRIMA ENTRY-----------//
            int id1 = rs.getInt("ID");
            String venditore1 = rs.getString("venditore");
            String nome1 = rs.getString("nome");
            String categoria1 = rs.getString("categoria");
            String colore1 = rs.getString("colore");
            String taglia1 = rs.getString("taglia");
            int prezzo1 = rs.getInt("prezzo");
            int disponibilità1 = rs.getInt("disponibilità");
            String descrizione1 = rs.getString("descrizione");
            String materiale1 = rs.getString("materiale");
            switch (categoria1) {
                case Constants.CAT_MAGLIETTA:{
                    scollo = rs.getString("maglietta_scollo");
                }
                case Constants.CAT_CINTURA:{
                    chiusura = rs.getString("cinta_chiusura");
                }
                case Constants.CAT_SCARPE:{
                    puntale = rs.getString("scarpa_puntale");
                }
                case Constants.CAT_PANTALONE:{
                    vestibilità = rs.getString("pantalone_vestibilità");
                }

            }
            prodotto = factoryProdotti.creaProdotto(id1, venditore1, nome1, categoria1, colore1, taglia1,
                    prezzo1, disponibilità1, descrizione1, materiale1,
                    puntale, scollo, chiusura, vestibilità);
            prodotti.add(prodotto);
            //-----------FINE PRIMA ENTRY-----------//

            while (rs.next()){
                int id = rs.getInt("ID");
                String venditore = rs.getString("venditore");
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                String colore = rs.getString("colore");
                String taglia = rs.getString("taglia");
                int prezzo = rs.getInt("prezzo");
                int disponibilità = rs.getInt("disponibilità");
                String descrizione = rs.getString("descrizione");
                String materiale = rs.getString("materiale");
                switch (categoria) {
                    case Constants.CAT_MAGLIETTA:{
                        scollo = rs.getString("maglietta_scollo");
                    }
                    case Constants.CAT_CINTURA:{
                        chiusura = rs.getString("cinta_chiusura");
                    }
                    case Constants.CAT_SCARPE:{
                        puntale = rs.getString("scarpa_puntale");
                    }
                    case Constants.CAT_PANTALONE:{
                        vestibilità = rs.getString("pantalone_vestibilità");
                    }

                }
                prodotto = factoryProdotti.creaProdotto(id, venditore, nome, categoria, colore, taglia,
                        prezzo, disponibilità, descrizione, materiale,
                        puntale, scollo, chiusura, vestibilità);
                prodotti.add(prodotto);
            }


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
        System.out.println("Accesso Prodotti effettuato con successo");
        return prodotti;
    }
}
