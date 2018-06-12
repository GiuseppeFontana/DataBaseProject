package Singletons;


import Entity.Structure;

public class SingletonStruct {
    private static SingletonStruct instance = null;
    private Structure structure;
    private int numeroSegmenti;

    protected SingletonStruct() {
    }

    public static SingletonStruct getInstance() {
        if(instance == null) {
            instance = new SingletonStruct();
        }
        return instance;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public int getNumeroSegmenti() {
        return numeroSegmenti;
    }

    public void setNumeroSegmenti(int numeroSegmenti) {
        this.numeroSegmenti = numeroSegmenti;
    }
}
