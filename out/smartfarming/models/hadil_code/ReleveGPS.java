package out.smartfarming.models.hadil_code;

public class ReleveGPS extends Releve {
private double latitude;
private double longitude;



    public ReleveGPS(Capteur capteur, double latitude, double longitude) {
        super(capteur);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude( ){
        return longitude;
    }

    @Override
    public String AfficherReleve() {
        return "ReleveGPS{" +
                "dateHeure=" + getDateHeure() +
                ", capteur=" + getCapteur() +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
