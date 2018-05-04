package Bean;

import Entity.Bound;

public class Req10AllBoundsBean {
    private Bound bound;
    private String satellite;

    public Req10AllBoundsBean(){
    }

    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }
}
