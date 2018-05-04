package Bean;

import Entity.Structure;

public class Req5Bean {
    private int id;
    private Double lonCenter;
    private Double latCenter;
    private Double lonExtension;
    private Double latExtension;
    private int nSegmenti;

    public Req5Bean(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLonCenter() {
        return lonCenter;
    }

    public void setLonCenter(Double lonCenter) {
        this.lonCenter = lonCenter;
    }

    public Double getLatCenter() {
        return latCenter;
    }

    public void setLatCenter(Double latCenter) {
        this.latCenter = latCenter;
    }

    public Double getLonExtension() {
        return lonExtension;
    }

    public void setLonExtension(Double lonExtension) {
        this.lonExtension = lonExtension;
    }

    public Double getLatExtension() {
        return latExtension;
    }

    public void setLatExtension(Double latExtension) {
        this.latExtension = latExtension;
    }

    public int getnSegmenti() {
        return nSegmenti;
    }

    public void setnSegmenti(int nSegmenti) {
        this.nSegmenti = nSegmenti;
    }
}
