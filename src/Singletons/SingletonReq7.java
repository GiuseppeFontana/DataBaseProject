package Singletons;

import Bean.Req7Bean;
import java.util.ArrayList;

public class SingletonReq7 {
    private static SingletonReq7 instance = null;
    private ArrayList<Req7Bean> beans;

    protected SingletonReq7() {
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
}
