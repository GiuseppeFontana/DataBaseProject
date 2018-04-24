package Bean;

import Entity.Structure;

public class Req7Bean {
    private int idStructure;
    private String nameStructure;
    private String satellite;
    private int nSegmenti;

    public Req7Bean(){
    }

    public int getIdStructure() {
        return idStructure;
    }

    public void setIdStructure(int idStructure) {
        this.idStructure = idStructure;
    }

    public String getNameStructure() {
        return nameStructure;
    }

    public void setNameStructure(String nameStructure) {
        this.nameStructure = nameStructure;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public int getnSegmenti() {
        return nSegmenti;
    }

    public void setnSegmenti(int nSegmenti) {
        this.nSegmenti = nSegmenti;
    }
}
