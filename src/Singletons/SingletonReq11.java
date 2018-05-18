package Singletons;

import Bean.Req11_Bean;
import java.util.ArrayList;

public class SingletonReq11 {
    private static SingletonReq11 instance = null;

    private ArrayList<Req11_Bean> beans;
    private String sat;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    protected SingletonReq11() {
        // Exists only to defeat instantiation.
    }

    public static SingletonReq11 getInstance() {
        if (instance == null) {
            instance = new SingletonReq11();
        }
        return instance;
    }

    public ArrayList<Req11_Bean> getBeans() {
        return beans;
    }

    public void setBeans(ArrayList<Req11_Bean> beans) {
        this.beans = beans;
    }


}