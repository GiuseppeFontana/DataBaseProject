package Singletons;

import Bean.Req12_Bean;

import java.util.ArrayList;

public class SingletonReq12 {

    private Req12_Bean beans;

    private static SingletonReq12 instance = null;

    public Req12_Bean getBeans() {
        return beans;
    }

    public void setBeans(Req12_Bean beans) {
        this.beans = beans;
    }

    public static void setInstance(SingletonReq12 instance) {
        SingletonReq12.instance = instance;
    }

    protected SingletonReq12() {
        // Exists only to defeat instantiation.
    }

    public static SingletonReq12 getInstance() {
        if (instance == null) {
            instance = new SingletonReq12();
        }
        return instance;
    }

}
