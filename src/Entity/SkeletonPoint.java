package Entity;

public class SkeletonPoint {
    private int idStructure;
    private int idBranch;
    private int nProgressive;
    private char type;            //'S' o 'B'
    private double longitude;
    private double latitude;
    private double flux;

    public SkeletonPoint(){
    }

    public int getIdStructure() {
        return idStructure;
    }

    public void setIdStructure(int idStructure) {
        this.idStructure = idStructure;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public int getnProgressive() {
        return nProgressive;
    }

    public void setnProgressive(int nProgressive) {
        this.nProgressive = nProgressive;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getFlux() {
        return flux;
    }

    public void setFlux(double flux) {
        this.flux = flux;
    }
}
