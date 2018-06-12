package Singletons;

import Bean.Req10AllBoundsBean;
import Bean.Req10StarBean;
import Bean.Req9_10Bean;
import Entity.Bound;

import java.util.ArrayList;

public class SingletonReq10 {

    private static SingletonReq10 instance = null;

    //array di strutture con contorni che cadono nella regione
    private ArrayList<Req10AllBoundsBean> structuresInBeans;

    //array stelle con booleano per indicare se appartiene a struttura
    private ArrayList<Req10StarBean> starBeans;

    //array di tutti i bound di un satellite per il calcolo
    private ArrayList<Bound> structureBounds;

    //array di bean delle stelle da mostrare
    private ArrayList<Req9_10Bean> beansToShow;


    //valori da stampare
    private int total_false;
    private int total_true;

    private int unbound_false;
    private int prestellar_false;
    private int protostellar_false;

    private int unbound_true;
    private int prestellar_true;
    private int protostellar_true;

    protected SingletonReq10() {
    }

    public static SingletonReq10 getInstance() {
        if(instance == null) {
            instance = new SingletonReq10();
        }
        return instance;
    }

    public ArrayList<Req10AllBoundsBean> getStructuresInBeans() {
        return structuresInBeans;
    }

    public void setStructuresInBeans(ArrayList<Req10AllBoundsBean> structuresInBeans) {
        this.structuresInBeans = structuresInBeans;
    }

    public ArrayList<Req10StarBean> getStarBeans() {
        return starBeans;
    }

    public void setStarBeans(ArrayList<Req10StarBean> starBeans) {
        this.starBeans = starBeans;
    }

    public ArrayList<Bound> getStructureBounds() {
        return structureBounds;
    }

    public void setStructureBounds(ArrayList<Bound> structureBounds) {
        this.structureBounds = structureBounds;
    }

    public ArrayList<Req9_10Bean> getBeansToShow() {
        return beansToShow;
    }

    public void setBeansToShow(ArrayList<Req9_10Bean> beansToShow) {
        this.beansToShow = beansToShow;
    }

    public int getTotal_false() {
        return total_false;
    }

    public void setTotal_false(int total_false) {
        this.total_false = total_false;
    }

    public int getTotal_true() {
        return total_true;
    }

    public void setTotal_true(int total_true) {
        this.total_true = total_true;
    }

    public int getUnbound_false() {
        return unbound_false;
    }

    public void setUnbound_false(int unbound_false) {
        this.unbound_false = unbound_false;
    }

    public int getPrestellar_false() {
        return prestellar_false;
    }

    public void setPrestellar_false(int prestellar_false) {
        this.prestellar_false = prestellar_false;
    }

    public int getProtostellar_false() {
        return protostellar_false;
    }

    public void setProtostellar_false(int protostellar_false) {
        this.protostellar_false = protostellar_false;
    }

    public int getUnbound_true() {
        return unbound_true;
    }

    public void setUnbound_true(int unbound_true) {
        this.unbound_true = unbound_true;
    }

    public int getPrestellar_true() {
        return prestellar_true;
    }

    public void setPrestellar_true(int prestellar_true) {
        this.prestellar_true = prestellar_true;
    }

    public int getProtostellar_true() {
        return protostellar_true;
    }

    public void setProtostellar_true(int protostellar_true) {
        this.protostellar_true = protostellar_true;
    }
}
