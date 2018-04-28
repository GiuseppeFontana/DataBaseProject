package Bean;

public class Req8CircularBean {
    private Integer id;
    private String name;
    private String satellite;
    private Double boundLong;
    private Double boundLat;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getBoundLong() {
        return boundLong;
    }

    public void setBoundLong(Double boundLong) {
        this.boundLong = boundLong;
    }

    public Double getBoundLat() {
        return boundLat;
    }

    public void setBoundLat(Double boundLat) {
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
