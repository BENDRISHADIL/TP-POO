package smartfarming.models.hadil_code;

public class ReleveNumerique extends Releve{
   private Double valeur;
   private String unite ;//unite de mesure

    public ReleveNumerique(Capteur capteur, Double valeur, String unite) {
        super(capteur);
        this.valeur = valeur;
        this.unite = unite;
    }

    public Double getValeur() {
        return valeur;
    }

    public String getUnite() {
        return unite;
    }
    // ici je vérifie la valeur de releve par rapport a une plage de seuils de capteur
    public boolean verifierSeuil(PlageSeuils ps) {
        return ps.estHorsSeuil(valeur);
        
    }

    @Override
    public String afficher() {
    StringBuilder sb = new StringBuilder();
    sb.append("Relevé : ").append(valeur).append(" ").append(unite).append(" | ").append(getHorodatage());
    return sb.toString();  
      
    }
    @Override
public String toString() {
    return afficher();  // réutilise afficher() déjà défini
}

    
}
