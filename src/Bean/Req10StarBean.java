package Bean;

import Entity.Star;

public class Req10StarBean {
    private Star star;
    private boolean isInStructure;

    public Req10StarBean(){
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public boolean isInStructure() {
        return isInStructure;
    }

    public void setInStructure(boolean inStructure) {
        isInStructure = inStructure;
    }
}
