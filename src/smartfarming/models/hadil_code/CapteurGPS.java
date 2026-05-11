package smartfarming.models.hadil_code;

import smartfarming.enums.StatusCapteur;

public class CapteurGPS extends Capteur {
    private double latitude;
    private double longitude;

    
    public CapteurGPS(StatusCapteur status) {
        // Le GPS n'a pas de seuils numériques → plage neutre
        super("GPS", status, new PlageSeuils(-90, 90));
    }
    //--------------------------
    // Getters et Setters   
    //--------------------------

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    @Override
    public ReleveGPS envoyerReleve() {
        // Simulation de coordonnées GPS (Alger)
        double lat = 36.7372;
        double lon = 3.0867;

        //  StringBuilder pour afficher le message
        StringBuilder sb = new StringBuilder();
        sb.append(">>> CapteurGPS [").append(getCodeUnique()).append("]")
          .append(" envoie position : lat=").append(lat)
          .append(", lon=").append(lon);
        System.out.println(sb.toString());

        return new ReleveGPS(this, lat, lon);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CapteurGPS [").append(super.toString()).append("]");
        return sb.toString();
    }
}
