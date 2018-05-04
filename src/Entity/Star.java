package Entity;

public class Star {

    private int id;
    private String name;
    private double gLon;
    private double gLat;
    private double flux;
    private String type;

    public Star(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getgLon() {
        return gLon;
    }

    public void setgLon(double gLon) {
        this.gLon = gLon;
    }

    public double getgLat() {
        return gLat;
    }

    public void setgLat(double gLat) {
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
