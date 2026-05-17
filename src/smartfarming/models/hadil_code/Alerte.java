package smartfarming.models.hadil_code;

import smartfarming.enums.NiveauGravite;
import smartfarming.enums.StatutAlerte;

import java.time.LocalDateTime;

public class Alerte {
    private static int compteur = 0;
    private String id ;
    private NiveauGravite niveauGravite;
    private String description;
    private StatutAlerte statut;
    private Releve releveSource;
    private LocalDateTime horodatage; 


    public Alerte(Releve releveSource, NiveauGravite niveauGravite, String description) {
        this.id = "ALERTE-" + (++compteur);
        this.niveauGravite = niveauGravite;
        this.description = description;
        this.statut = StatutAlerte.ACTIVE;
        this.releveSource = releveSource;
        this.horodatage = LocalDateTime.now();
    }




    //getters

    public String getId() {
        return id;
    }
    public NiveauGravite getNiveauGravite() {
        return niveauGravite;
    }
    public String getDescription() {
        return description;
    }
    public StatutAlerte getStatut() {
        return statut;
    }
    public Releve getReleveSource() {
        return releveSource;
    }
    public LocalDateTime getHorodatage() {
        return horodatage;
    }
    //acquitter l'alerte
    public void acquitter() {
        this.statut = StatutAlerte.ACQUITTEE;
    }
    //supprimer l'alerte
    public void supprimer() {
        this.statut =StatutAlerte.SUPPRIMEE;
    }
    //afficher les details de l'alerte ET EN COLERS
    public String afficher() {
        StringBuilder sb = new StringBuilder();
        
        String couleur;

        if (niveauGravite == NiveauGravite.CRITIQUE) {
            couleur = "\u001B[31m";
        } else {
            couleur = "\u001B[33m";
        }
        
        String reset = "\u001B[0m";
        
        sb.append(couleur).append(id).append(reset)
          .append(" | ").append(niveauGravite).append(" | ").append(horodatage)
          .append("\n   Description: ").append(description)
          .append("\n   Capteur: ").append(releveSource.getCapteur().getCodeUnique())
          .append(" | Statut: ").append(statut);
        
        return sb.toString();
    }


    @Override
    public String toString() {
        return afficher();
    }

    
}
