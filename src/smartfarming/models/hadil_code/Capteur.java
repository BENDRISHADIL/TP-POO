package smartfarming.models.hadil_code;

import smartfarming.enums.StatusCapteur;
import smartfarming.interfaces.Suspendable;
public abstract class Capteur implements Suspendable {
    static int compteur = 1;
    private String codeUnique;
    private StatusCapteur status;
    private PlageSeuils plageSeuils;

/**
 * Constructeur de la classe Capteur    
 * @param prefixe ex: "ENV", "SOL", "BIO", "EAU", "GPS"
 */

    public Capteur(String prefixe, StatusCapteur status, PlageSeuils plageSeuils) {
        this.codeUnique = prefixe + "-" +compteur;//je veut la form ex: "ENV-0"
        this.status = status;
        this.plageSeuils = plageSeuils;
        compteur++;
    }


    
    // -------------------------
    // Getters 
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
