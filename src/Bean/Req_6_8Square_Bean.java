package Bean;

public class Req_6_8Square_Bean {
    private int id;
    private String name;
    private String satellite;


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

    public Req_6_8Square_Bean(Integer id, String name, String satellite) {
        this.id = id;
        this.name = name;
        this.satellite = satellite;
    }
}
