package smartfarming.models;

public class ExigencePedologique {

    private double phMin;
    private double phMax;
    private double humiditeMin;
    private double humiditeMax;
    private double azoteMin;
    private double azoteMax;

    public ExigencePedologique(double phMin, double phMax,double humiditeMin, double humiditeMax,double azoteMin, double azoteMax) {
        this.phMin = phMin;
        this.phMax = phMax;
        this.humiditeMin = humiditeMin;
        this.humiditeMax = humiditeMax;
        this.azoteMin = azoteMin;
        this.azoteMax = azoteMax;
    }

    // Getters
    public double getPhMin() { return phMin; }
    public double getPhMax() { return phMax; }
    public double getHumiditeMin() { return humiditeMin; }
    public double getHumiditeMax() { return humiditeMax; }
    public double getAzoteMin() { return azoteMin; }
    public double getAzoteMax() { return azoteMax; }
}