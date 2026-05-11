package smartfarming.models.hadil_code;
import smartfarming.enums.StatusCapteur;
import smartfarming.enums.TypeMesure;
public class CapteurBiometrique extends CapteurNumerique {
    
    public CapteurBiometrique(StatusCapteur status, PlageSeuils plageSeuils,
                              TypeMesure typeMesure, String uniteMesure) {
        super("BIO", status, plageSeuils, uniteMesure, typeMesure);
    }

    @Override
    public ReleveNumerique envoyerReleve() {
        double valeurSimulee = simulerMesure();
        StringBuilder sb = new StringBuilder();
        sb.append(">>> CapteurBiometrique [").append(getCodeUnique()).append("]")
          .append(" envoie un relevé : ")
          .append(valeurSimulee).append(" ").append(getUniteMesure());
        System.out.println(sb.toString());

        return new ReleveNumerique(this, valeurSimulee, getUniteMesure());
    }

    private double simulerMesure() {
        switch (getTypeMesure()) {
            case TEMPERATURE: return 38.6; // température corporelle normale vache
            case HUMIDITE:    return 72.0; // niveau d'activité simulé
            default:          return 0.0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CapteurBiometrique [").append(super.toString()).append("]");
        return sb.toString();
    }

    
}
