package smartfarming.models;

public class LimitesGeographiques {

    private double latitudeMin;
    private double latitudeMax;
    private double longitudeMin;
    private double longitudeMax;

    public LimitesGeographiques(double latitudeMin, double latitudeMax,
                                 double longitudeMin, double longitudeMax) {
        this.latitudeMin = latitudeMin;
        this.latitudeMax = latitudeMax;
        this.longitudeMin = longitudeMin;
        this.longitudeMax = longitudeMax;
    }

    public double getLatitudeMin() { return latitudeMin; }
    public double getLatitudeMax() { return latitudeMax; }
    public double getLongitudeMin() { return longitudeMin; }
    public double getLongitudeMax() { return longitudeMax; }
}