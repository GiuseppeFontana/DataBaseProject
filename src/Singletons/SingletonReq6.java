package Singletons;


import Bean.Req6Bean;
import java.util.ArrayList;

public class SingletonReq6 {
    private static SingletonReq6 instance = null;
    private ArrayList<Req6Bean> beans;
    private Integer totaleStrutture;

    protected SingletonReq6() {
        // Exists only to defeat instantiation.
    }

    public static SingletonReq6 getInstance() {
        if(instance == null) {
            instance = new SingletonReq6();
        }
        return instance;
    }

    public ArrayList<Req6Bean> getBeans() {
        return beans;
    }

    public void setBeans(ArrayList<Req6Bean> b) {
        beans = b;
    }

    public Integer getTotaleStrutture() {
        return totaleStrutture;
    }

    public void setTotaleStrutture(Integer totaleStrutture) {
        this.totaleStrutture = totaleStrutture;
    }
}