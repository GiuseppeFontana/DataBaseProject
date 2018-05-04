package Singletons;

import Bean.Req8CircularBean;
import Bean.Req6_8SquareBean;

import java.util.ArrayList;

public class SingletonReq8 {
    private static SingletonReq8 instance = null;
    private ArrayList<Req6_8SquareBean> req_6_8Square_beans;
    private ArrayList<Req8CircularBean> req8CircularBeans;

    protected SingletonReq8() {
        // Exists only to defeat instantiation.
    }

    public static SingletonReq8 getInstance() {
        if(instance == null) {
            instance = new SingletonReq8();
        }
        return instance;
    }

    public ArrayList<Req6_8SquareBean> getBeans() {
        return req_6_8Square_beans;
    }

    public void setBeans(ArrayList<Req6_8SquareBean> b) {
        req_6_8Square_beans = b;
    }

    public ArrayList<Req8CircularBean> getReq8CircularBeans() {
        return req8CircularBeans;
    }

    public void setReq8CircularBeans(ArrayList<Req8CircularBean> req8CircularBeans) {
        this.req8CircularBeans = req8CircularBeans;
    }
}
