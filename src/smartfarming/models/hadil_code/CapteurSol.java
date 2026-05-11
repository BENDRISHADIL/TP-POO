package smartfarming.models.hadil_code;

import smartfarming.enums.StatusCapteur;
import smartfarming.enums.TypeMesure;

public class CapteurSol extends CapteurNumerique {
  
    public CapteurSol(StatusCapteur status, PlageSeuils plageSeuils,
                      TypeMesure typeMesure, String uniteMesure) {
        super( "SOL", status, plageSeuils, uniteMesure,typeMesure);
    }
 
    @Override
    public  ReleveNumerique envoyerReleve() {
        double valeurSimulee= simulerMesure();
        StringBuilder sb = new StringBuilder();
        sb.append(">>> CapteurSol [").append(getCodeUnique()).append("]")
          .append(" envoie un relevé : ")
          .append(valeurSimulee).append(" ").append(getUniteMesure());
        System.out.println(sb.toString());

        return new ReleveNumerique(this, valeurSimulee, getUniteMesure());
    }
    //thus function i just use it to test after ra7 n7iha
    private double simulerMesure() {
        switch (getTypeMesure()) {
            case PH:                return 6.8;   // simule un pH de sol normal
            case TAUX_HUMIDITE_SOL: return 40.0;  // simule 40% d'humidité
            case TENEUR_AZOTE:      return 15.2;  // simule 15.2 mg/kg d'azote
            default:                return 0.0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CapteurSol [").append(super.toString()).append("]");
        return sb.toString();
    }

}
