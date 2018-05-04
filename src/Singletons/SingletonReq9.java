package Singletons;

import Bean.Req9_10Bean;
import Entity.Bound;
import Entity.Star;

import java.util.ArrayList;

public class SingletonReq9 {
    private static SingletonReq9 instance = null;
    private ArrayList<Star> stars;
    private ArrayList<Bound> structureBounds;
    private ArrayList<Req9_10Bean> beans;

    private int unbound;
    private int prestellar;
    private int protostellar;

    protected SingletonReq9() {
        // Exists only to defeat instantiation.
    }

    public static SingletonReq9 getInstance() {
        if(instance == null) {
            instance = new SingletonReq9();
        }
        return instance;
    }

    public ArrayList<Star> getStars() {
        return stars;
    }

    public void setStars(ArrayList<Star> stars) {
        this.stars = stars;
    }

    public ArrayList<Bound> getStructureBounds() {
        return structureBounds;
    }

    public void setStructureBounds(ArrayList<Bound> structureBounds) {
        this.structureBounds = structureBounds;
    }

    public ArrayList<Req9_10Bean> getBeans() {
        return beans;
    }

    public void setBeans(ArrayList<Req9_10Bean> beans) {
        this.beans = beans;
    }

    public int getUnbound() {
        return unbound;
    }

    public void setUnbound(int unbound) {
        this.unbound = unbound;
    }

    public int getPrestellar() {
        return prestellar;
    }

    public void setPrestellar(int prestellar) {
        this.prestellar = prestellar;
    }

    public int getProtostellar() {
        return protostellar;
    }

    public void setProtostellar(int protostellar) {
        this.protostellar = protostellar;
    }
}
