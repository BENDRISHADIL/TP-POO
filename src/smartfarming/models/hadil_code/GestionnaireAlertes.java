package smartfarming.models.hadil_code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import smartfarming.enums.StatutAlerte;

public class GestionnaireAlertes {
    private static GestionnaireAlertes instance;  // Singleton
    private List<Alerte> toutesAlertes;
    
    private GestionnaireAlertes() {
        this.toutesAlertes = new ArrayList<>();
    }

    /** Retourne l'instance unique du gestionnaire (Singleton) */
    public static synchronized GestionnaireAlertes getInstance() {
        if (instance == null) {
            instance = new GestionnaireAlertes();
        }
        return instance;
    }
    public void ajouterAlerte(Alerte alerte) {
        toutesAlertes.add(alerte);}


    /**
 * Retourne toutes les alertes actives (non traitées).
 * Utilise if-else au lieu de Stream.
 */
public List<Alerte> obtenirAlertesActives() {
    List<Alerte> alertesActives = new ArrayList<>();
    
    // Parcourir toutes les alertes
    for (Alerte alerte : toutesAlertes) {
        // Vérifier si l'alerte est ACTIVE
        if (alerte.getStatut() == smartfarming.enums.StatutAlerte.ACTIVE) {
            alertesActives.add(alerte);
        }
    }
    
    return alertesActives;
}    
    
   public List<Alerte> obtenirAlertesAcquittees() {
        List<Alerte> alertesAcquittees = new ArrayList<>();
        
        for (Alerte alerte : toutesAlertes) {
            if (alerte.getStatut() == StatutAlerte.ACQUITTEE) {
                alertesAcquittees.add(alerte);
            }
        }
        
        return alertesAcquittees;
    }  
    public List<Alerte> obtenirAlertesSupprimees() {
        List<Alerte> alertesSupprimees = new ArrayList<>();
        
        for (Alerte alerte : toutesAlertes) {
            if (alerte.getStatut() == StatutAlerte.SUPPRIMEE) {
                alertesSupprimees.add(alerte);
            }
        }
        
        return alertesSupprimees;
    } 
    public List<Alerte> obtenirToutHistorique() {
        return new ArrayList<>(toutesAlertes);
    } 
    public List<Alerte> obtenirAlertesPourDate(LocalDate date) {
        List<Alerte> alertesDuJour = new ArrayList<>();
        
        for (Alerte alerte : toutesAlertes) {
            if (alerte.getHorodatage().toLocalDate().equals(date)) {
                alertesDuJour.add(alerte);
            }
        }
        
        return alertesDuJour;
    }
    public List<Alerte> obtenirAlertesParCapteur(String codeUniqueCapteur) {
        List<Alerte> alertesCapteur = new ArrayList<>();
        
        for (Alerte alerte : toutesAlertes) {
            if (alerte.getReleveSource().getCapteur().getCodeUnique()
                    .equals(codeUniqueCapteur)) {
                alertesCapteur.add(alerte);
            }
        }
        
        return alertesCapteur;
    }
}
