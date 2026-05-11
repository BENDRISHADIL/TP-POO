/*** Relevé GPS contenant des coordonnées géographiques.
     Utilisé par le CapteurGPS pour localiser les animaux.*/

package smartfarming.models.hadil_code;

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

    public double getLongitude() {
    return longitude;
}

    @Override
    public String afficher() {
    StringBuilder sb = new StringBuilder();
    sb.append("GPS : lat=").append(latitude)
      .append(", lon=").append(longitude)
      .append(" | ").append(getHorodatage());
    return sb.toString();
    
}
    @Override
    public String toString() {
        return afficher();  // réutilise afficher() déjà défini
    }
}
