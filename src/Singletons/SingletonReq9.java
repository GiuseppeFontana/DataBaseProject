package Singletons;

import Bean.Req9Bean;
import Entity.Bound;
import Entity.Star;

import java.util.ArrayList;

public class SingletonReq9 {
    private static SingletonReq9 instance = null;
    private ArrayList<Star> stars;
    private ArrayList<Bound> structureBounds;
    private ArrayList<Req9Bean> beans;

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

    public ArrayList<Req9Bean> getBeans() {
        return beans;
    }

    public void setBeans(ArrayList<Req9Bean> beans) {
        this.beans = beans;
    }
}
