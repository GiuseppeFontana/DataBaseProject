package Singletons;

import Bean.BeanId;

import java.util.ArrayList;

public class SingletonId {
    private static SingletonId instance = null;
    private ArrayList<BeanId> beans;
    private String satellite;

    protected SingletonId() {
        // Exists only to defeat instantiation.
    }

    public static SingletonId getInstance() {
        if(instance == null) {
            instance = new SingletonId();
        }
        return instance;
    }

    public ArrayList<BeanId> getBeans() {
        return beans;
    }

    public void setBeans(ArrayList<BeanId> beans) {
        this.beans = beans;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }
}
