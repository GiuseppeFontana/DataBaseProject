package Bean;

public class Req8CircularBean {
    private int id;
    private String name;
    private String satellite;
    private double boundLong;
    private double boundLat;


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

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public double getBoundLong() {
        return boundLong;
    }

    public void setBoundLong(double boundLong) {
        this.boundLong = boundLong;
    }

    public double getBoundLat() {
        return boundLat;
    }

    public void setBoundLat(double boundLat) {
        this.boundLat = boundLat;
    }

    public Req8CircularBean(Integer id, String name, String satellite, Double boundLong, Double boundLat) {
        this.id = id;
        this.name = name;
        this.satellite = satellite;
        this.boundLong = boundLong;
        this.boundLat = boundLat;
    }
}
