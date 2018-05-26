package Singletons;

import Bean.Req12_Bean;
import Bean.Req12_BeanToShow;
import Entity.SkeletonPoint;

import java.util.ArrayList;

public class SingletonReq12 {

    private ArrayList<Req12_Bean> beans;

    private ArrayList<SkeletonPoint> skeletonPoints;

    public ArrayList<Req12_BeanToShow> getBeanToShows() {
        return beanToShows;
    }

    public void setBeanToShows(ArrayList<Req12_BeanToShow> beanToShows) {
        this.beanToShows = beanToShows;
    }

    private ArrayList<Req12_BeanToShow> beanToShows;

    private static SingletonReq12 instance = null;

    public ArrayList<SkeletonPoint> getSkeletonPoints() {
        return skeletonPoints;
    }

    public void setSkeletonPoints(ArrayList<SkeletonPoint> skeletonPoints) {
        this.skeletonPoints = skeletonPoints;
    }



    public ArrayList<Req12_Bean> getBeans() {
        return beans;
    }

    public void setBeans(ArrayList<Req12_Bean> beans) {
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
