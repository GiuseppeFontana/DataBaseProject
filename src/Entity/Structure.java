package Entity;

public class    Structure {

    private int id;
    private String name;
    private Double flux;
    private Double meanDens;
    private int meanTemp;
    private int ellipt;
    private int contrast;
    private String satellite;
    private String instrument;

    public Structure(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFlux() {
        return flux;
    }

    public void setFlux(Double flux) {
        this.flux = flux;
    }

    public Double getMeanDens() {
        return meanDens;
    }

    public void setMeanDens(Double meanDens) {
        this.meanDens = meanDens;
    }

    public Integer getMeanTemp() {
        return meanTemp;
    }

    public void setMeanTemp(Integer meanTemp) {
        this.meanTemp = meanTemp;
    }

    public Integer getEllipt() {
        return ellipt;
    }

    public void setEllipt(Integer ellipt) {
        this.ellipt = ellipt;
    }

    public Integer getContrast() {
        return contrast;
    }

    public void setContrast(Integer contrast) {
        this.contrast = contrast;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    /*
    1) Id	della	struttura	nella	mappa	HIGAL
2) Nome	identificativo
3) Flusso	totale	nella	regione
4) Densità	media
5) Temperatura	media
6) Ellitticità
7) Contrasto	del	filamento	rispetto	al	suo	contorno+
8) Nome	satellite
9) Nome	strumento
     */
}
