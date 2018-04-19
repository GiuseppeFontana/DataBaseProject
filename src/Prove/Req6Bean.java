package Prove;

public class Req6Bean {
    private Integer id;
    private String name;
    private String satellite;
    private Integer totaleStrutture;


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

    public Integer getTotaleStrutture() {
        return totaleStrutture;
    }

    public void setTotaleStrutture(Integer totaleStrutture) {
        this.totaleStrutture = totaleStrutture;
    }

    public Req6Bean(Integer id, String name, String satellite, Integer totaleStrutture) {
        this.id = id;
        this.name = name;
        this.satellite = satellite;
        this.totaleStrutture = totaleStrutture;
    }
}
