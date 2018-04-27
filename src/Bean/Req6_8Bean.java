package Bean;

public class Req6_8Bean {
    private Integer id;
    private String name;
    private String satellite;


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

    public Req6_8Bean(Integer id, String name, String satellite) {
        this.id = id;
        this.name = name;
        this.satellite = satellite;
    }
}
