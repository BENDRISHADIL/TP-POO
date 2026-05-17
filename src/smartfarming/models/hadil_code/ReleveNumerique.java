package smartfarming.models.hadil_code;

public class ReleveNumerique extends Releve{
   private Double valeur;
   private String unite ;//unite de mesure

    public ReleveNumerique(Capteur capteur, Double valeur, String unite) {
        super(capteur);
        this.valeur = valeur;
        this.unite = unite;
        capteur.ajouterAuHistorique(this);//Sauvegarde dans l'historique du capteur
        verifierEtDeclencherAlerte(capteur, valeur);
    }


    private void verifierEtDeclencherAlerte(Capteur capteur ,Double valeur){
       //Récupérer les seuils
        PlageSeuils ps = capteur.getPlageSeuils();
        double min = ps.getValeurMin();
        double max = ps.getValeurMax(); 

        //Calculer la marge de 10%
        double marge = (max - min) * 0.10;
        //deeclaration du variable 
        GestionnaireAlertes gestionnaire = GestionnaireAlertes.getInstance();
        smartfarming.enums.NiveauGravite niveau = null;  // sera défini selon le cas
        boolean declencherAlerte = false;  // flag pour savoir si alerte à créer
        String description = "";  // message de l'alerte
        //verification : first case : hors seuils->critique
        if (valeur < min || valeur > max) {
            
            declencherAlerte = true;
            niveau = smartfarming.enums.NiveauGravite.CRITIQUE;
            
            // Message personnalisé selon si trop basse ou trop haute
            if (valeur < min) {
                description = "Valeur trop basse : " + String.format("%.2f", valeur) 
                            + " (min: " + String.format("%.2f", min) + ")";
            } else {
                description = "Valeur trop haute : " + String.format("%.2f", valeur) 
                            + " (max: " + String.format("%.2f", max) + ")";
            }

        //second case : dans les 10% des limites -> avertissement
        } else if (valeur < min + marge || valeur > max - marge) {
            
            declencherAlerte = true;
            niveau = smartfarming.enums.NiveauGravite.AVERTISSEMENT;
            
            // Message personnalisé selon si proche min ou proche max
            if (valeur < min + marge) {
                description = "Alerte : valeur proche du minimum : " + String.format("%.2f", valeur) + " (min: " + String.format("%.2f", min) + ")";
            } else {
                description = "Alerte : valeur proche du maximum : " + String.format("%.2f", valeur) + " (max: " + String.format("%.2f", max) + ")";
            }
            // Pas d'alerte si la valeur est bien dans la plage
        } else {
            declencherAlerte = false;  // Pas d'alerte
        }

        // Si une alerte doit être déclenchée, on la crée et l'ajoute au gestionnaire
        if (declencherAlerte) {
            Alerte alerte = new Alerte(this, niveau, description);
            gestionnaire.ajouterAlerte(alerte);
        }
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
