package smartfarming.models.hadil_code;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import smartfarming.enums.StatusCapteur;
import smartfarming.interfaces.Suspendable;



public abstract class Capteur implements Suspendable {
    static int compteur = 0;
    private String codeUnique;
    private StatusCapteur status;
    private PlageSeuils plageSeuils;
    private List<Releve> historique; // pour stocker les relevés envoyés par le capteur
   
   
    
    /**
     * Constructeur de la classe Capteur    
     * @param prefixe ex: "ENV", "SOL", "BIO", "EAU", "GPS"
     */

    public Capteur(String prefixe, StatusCapteur status, PlageSeuils plageSeuils) {
        compteur++;
        this.codeUnique = prefixe + "-" +compteur;//je veut la form ex: "ENV-0"
        this.status = status;
        this.plageSeuils = plageSeuils;
        this.historique = new ArrayList<>();
    }


    
    // -------------------------
    // Getters and setters
    // -------------------------
    
    public String getCodeUnique() {
        return codeUnique;
    }
    public StatusCapteur getStatus() {
        return status;
    }
    public PlageSeuils getPlageSeuils() {
        return plageSeuils;
    }
   
    public void changerStatut(StatusCapteur s) {
        this.status = s; //this setter is to change the status of the capteur by hand
    }

    //-----------------------
    //histarique 
    //-----------------------
     
    
    
    
    /**
     * Ajoute un relevé à l'historique.
     * Appelé automatiquement quand un relevé est créé.
     * 
     * @param releve le relevé à enregistrer
     */
    public void ajouterAuHistorique(Releve releve) {
        historique.add(releve);

        StringBuilder sb = new StringBuilder();
        sb.append(" Relevé enregistré pour ").append(codeUnique)
          .append(" | Historique : ").append(historique.size());
        // System.out.println(sb.toString()); // décommenter si tu veux voir
    }



    /**
     * Retourne tous les relevés de ce capteur.
     * 
     * @return liste de tous les relevés
     */
    public List<Releve> obtenirToutHistorique() {
        return new ArrayList<>(historique);
    }


    /**
 * Filtre l'historique par plage de dates (sans heures).
 * 
 * @param debut date de debut (incluse)
 * @param fin   date de fin (incluse)
 * @return liste des relevés filtrés
 */
public List<Releve> consulterHistorique(java.time.LocalDate debut, java.time.LocalDate fin) {
    LocalDateTime debutTime = debut.atStartOfDay();
    LocalDateTime finTime = fin.atTime(23, 59, 59);
    
    return historique.stream()
        .filter(releve -> {
            LocalDateTime time = releve.getHorodatage();
            
            boolean apresDebut = !time.isBefore(debutTime);
            boolean avantFin = !time.isAfter(finTime);
            
            return apresDebut && avantFin;
        })
        .collect(Collectors.toList());
}

    /**
     * Filtre l'historique par plage de dates ET heures 
     * 
     * @param debut date/heure de début qui l'utilisateur veut affiche entre
     * @param fin   date/heure de fin qui l'utilisateur veut affiche entre
     * @return liste des relevés filtrés par la date et l'heure
     */
    public List<Releve> consulterHistoriqueDetaille(LocalDateTime debut, LocalDateTime fin) {
        
        return historique.stream()
            .filter(releve -> {
                LocalDateTime time = releve.getDateHeure();
                
                boolean apresDebut = !time.isBefore(debut);
                boolean avantFin = !time.isAfter(fin);
                
                return apresDebut && avantFin;
            })
            .collect(Collectors.toList());
    }
    //this function is just to returne the number of releves in the historique of the capteur
    public int getTailleHistorique() {
        return historique.size();
    }

    //in case on a une longe durée en peut efface les capteurs qui sont dans l'historique depuis plus de 30 jours
    public void effacerHistorique() {
        historique.clear();
        StringBuilder sb = new StringBuilder();
        sb.append(" Historique de ").append(codeUnique).append(" effacé.");
        System.out.println(sb.toString());
    }




    // -------------------------
    // Implémentation de Suspendable
    // -------------------------

    @Override
    public void suspendre() {
        this.status = StatusCapteur.SUSPENDU;
    

     // StringBuilder pour construire le message de sortie
        StringBuilder sb = new StringBuilder();
        sb.append(">>> Capteur ")
          .append(codeUnique)
          .append(" suspendu. Statut : ")
          .append(status);

        System.out.println(sb.toString()); 
    }

    @Override
    public void reactiver(){
        this.status = StatusCapteur.ACTIF;
        StringBuilder sb = new StringBuilder();
        sb.append(">>> Capteur ")
          .append(codeUnique)
          .append(" réactivé. Statut : ")
          .append(status);
        System.out.println(sb.toString());
    }

    @Override
    public boolean estSuspendu() {
        return this.status == StatusCapteur.SUSPENDU;
    }

    // -------------------------
    // Méthode abstraite
    // -------------------------

    
    /** chaque sous classe affiche ces propres informations avec sa propre logique */ 
        public abstract Releve envoyerReleve();

    // -------------------------
    //stringBuilder pour afficher les informations du capteur
    // -------------------------
  
    @Override public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Capteur [codeUnique=").append(codeUnique)
          .append(", status=").append(status)
          .append(", plageSeuils=").append(plageSeuils)
          .append("]");
        return sb.toString();
    }    
}
