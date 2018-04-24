package Singletons;

import Bean.Req7Bean;
import java.util.ArrayList;

public class SingletonReq7 {
    private static SingletonReq7 instance = null;
    private ArrayList<Req7Bean> beans;
    private Integer struttureTrovate;

    protected SingletonReq7() {
        // Exists only to defeat instantiation.
    }

    public static SingletonReq7 getInstance() {
        if (instance == null) {
            instance = new SingletonReq7();
        }
        return instance;
    }

    public ArrayList<Req7Bean> getBeans() {
        return beans;
    }

    public void setBeans(ArrayList<Req7Bean> beans) {
        this.beans = beans;
    }

    public Integer getStruttureTrovate() {
        return struttureTrovate;
    }

    public void setStruttureTrovate(Integer struttureTrovate) {
        this.struttureTrovate = struttureTrovate;
    }
}
