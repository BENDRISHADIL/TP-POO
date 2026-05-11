package smartfarming.models.hadil_code;
import smartfarming.enums.StatusCapteur;
import smartfarming.enums.TypeMesure ;


public abstract class CapteurNumerique extends Capteur {
    private TypeMesure typeMesure;
    private String uniteMesure;//ici le type de unit peut etre mm/C/... dependes on the type of the capteure
   
    
    
    /**
     * Constructeur
     * @param prefixe     ex: "ENV", "SOL", "BIO", "EAU"
     */
   
    public CapteurNumerique(String prefixe, StatusCapteur status,PlageSeuils plageSeuils,String uniteMesure,TypeMesure typeMesure){
        super(prefixe, status, plageSeuils);
        this.typeMesure = typeMesure;
        this.uniteMesure = uniteMesure;
    }
   //-------------------------
   // Getters et Setters    
   //-------------------------

    public TypeMesure getTypeMesure() {
        return typeMesure;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }
    //-------------------------
    //methodes abetraites
    //-------------------------
   @Override
    public abstract ReleveNumerique envoyerReleve();

     // -------------------------
    // toString
    // -------------------------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CapteurNumerique [")
          .append(super.toString())         // infos de Capteur (code, statut, seuils)
          .append(", typeMesure=").append(typeMesure)
          .append(", uniteMesure=").append(uniteMesure)
          .append("]");
        return sb.toString();
    }

  
    
    
}
