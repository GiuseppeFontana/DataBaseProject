package Entity;

public class Star {

    private long id;
    private String name;
    private float gLon;
    private float gLat;
    private double flux;
    private String type;

    public Star(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getgLon() {
        return gLon;
    }

    public void setgLon(float gLon) {
        this.gLon = gLon;
    }

    public float getgLat() {
        return gLat;
    }

    public void setgLat(float gLat) {
        this.gLat = gLat;
    }

    public double getFlux() {
        return flux;
    }

    public void setFlux(double flux) {
        this.flux = flux;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
