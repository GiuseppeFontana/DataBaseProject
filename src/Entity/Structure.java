package Entity;

public class    Structure {

    private int id;
    private String name;
    private Double flux;
    private Double meanDens;
    private Double meanTemp;
    private Double ellipt;
    private Double contrast;
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

    public Double getMeanTemp() {
        return meanTemp;
    }

    public void setMeanTemp(Double meanTemp) {
        this.meanTemp = meanTemp;
    }

    public Double getEllipt() {
        return ellipt;
    }

    public void setEllipt(Double ellipt) {
        this.ellipt = ellipt;
    }

    public Double getContrast() {
        return contrast;
    }

    public void setContrast(Double contrast) {
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

    public Structure create(int id, String name, double flux, double meanDens, Double meanTemp, Double ellipt, Double contrast, String satellite, String instrument) {

        Structure structure = new Structure();

        structure.setId(id);
        structure.setName(name);
        structure.setFlux(flux);
        structure.setMeanDens(meanDens);
        structure.setMeanTemp(meanTemp);
        structure.setEllipt(ellipt);
        structure.setContrast(contrast);
        structure.setSatellite(satellite);
        structure.setInstrument(instrument);

        return structure;
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
