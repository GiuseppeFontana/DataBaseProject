package Singletons;

import Bean.Req5Bean;

public class SingletonReq5 {
    private static SingletonReq5 instance = null;
    private Req5Bean bean;

    protected SingletonReq5() {
        // Exists only to defeat instantiation.
    }

    public static SingletonReq5 getInstance() {
        if(instance == null) {
            instance = new SingletonReq5();
        }
        return instance;
    }

    public Req5Bean getBean() {
        return bean;
    }

    public void setBean(Req5Bean bean) {
        this.bean = bean;
    }
}
