package Singletons;

import Bean.Req6_8Bean;

import java.util.ArrayList;

public class SingletonReq8 {
    private static SingletonReq8 instance = null;
    private ArrayList<Req6_8Bean> beans;

    protected SingletonReq8() {
        // Exists only to defeat instantiation.
    }

    public static SingletonReq8 getInstance() {
        if(instance == null) {
            instance = new SingletonReq8();
        }
        return instance;
    }

    public ArrayList<Req6_8Bean> getBeans() {
        return beans;
    }

    public void setBeans(ArrayList<Req6_8Bean> b) {
        beans = b;
    }
}
