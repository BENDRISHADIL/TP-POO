package smartfarming.models.hadil_code;
import smartfarming.enums.StatusCapteur;
import smartfarming.enums.TypeMesure;

public class CapteurEau extends CapteurNumerique {

    public CapteurEau(StatusCapteur status,PlageSeuils plageSeuils,TypeMesure typeMesure,String uniteMesure) {
        super("EAU", status, plageSeuils, uniteMesure, typeMesure);
    }
    @Override
    public ReleveNumerique envoyerReleve() {
        double valeurSimulee = simulerMesure();
        StringBuilder sb = new StringBuilder();
        sb.append(">>> CapteurEau [").append(getCodeUnique()).append("]")
          .append(" envoie un relevé : ")
          .append(valeurSimulee).append(" ").append(getUniteMesure());
        System.out.println(sb.toString());

        return new ReleveNumerique(this, valeurSimulee, getUniteMesure());

    }
    /** Simule une valeur selon le type de mesure configuré */
    private double simulerMesure() {
        switch (getTypeMesure()) {
            case TEMPERATURE:     return 18.0;
            case OXYGENE_DISSOUS: return 7.5;
            case PH:              return 7.2;
            default:              return 0.0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CapteurEau [").append(super.toString()).append("]");
        return sb.toString();
    }








}
