package smartfarming.models.hadil_code;
import smartfarming.enums.StatusCapteur;
import smartfarming.enums.TypeMesure;

public class CapteurEnvironnemental extends CapteurNumerique {
    
    public CapteurEnvironnemental(StatusCapteur status, PlageSeuils plageSeuils, TypeMesure typeMesure, String uniteMesure) {
        super("ENV", status, plageSeuils, uniteMesure, typeMesure);
    }

    @Override
    public ReleveNumerique envoyerReleve() {
        double valeurSimulee = simulerMesure();
        StringBuilder sb = new StringBuilder();
        sb.append(">>> CapteurEnvironnemental [").append(getCodeUnique()).append("]")
          .append(" envoie un relevé : ")
          .append(valeurSimulee).append(" ").append(getUniteMesure());
        System.out.println(sb.toString());

        return new ReleveNumerique(this, valeurSimulee, getUniteMesure());
    }

    private double simulerMesure() {
        switch (getTypeMesure()) {
            case TEMPERATURE: return 25.0; // simule 25°C
            case HUMIDITE:    return 60.0; // simule 60% d'humidité
           
            default:          return 0.0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CapteurEnvironnemental [").append(super.toString()).append("]");
        return sb.toString();
    }

}