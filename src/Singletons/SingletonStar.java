package Singletons;

import Entity.Star;

public class SingletonStar {
    private static SingletonStar instance = null;
    private Star star;

    protected SingletonStar() {
    }

    public static SingletonStar getInstance() {
        if(instance == null) {
            instance = new SingletonStar();
        }
        return instance;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }
}
