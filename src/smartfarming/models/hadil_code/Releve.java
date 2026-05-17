package smartfarming.models.hadil_code;

import java.time.LocalDateTime;
public abstract class Releve {
    private LocalDateTime dateHeure;
    private Capteur capteur;

    /**
     * Constructeur — horodaté automatiquement à la création.
     * 
     * @param capteur le capteur qui a émis ce relevé
     */
    public Releve(Capteur capteur) {
        this.dateHeure = LocalDateTime.now();// Enregistre la date et l'heure du relevé a momnet de sa creation
        this.capteur = capteur;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public Capteur getCapteur() {
        return capteur;
    }
    //--------------------------
    //gerters 
    //--------------------------
    public String getHorodatage() {
        return dateHeure.toString();
    }
    public String getCodeCapteur() {
        return capteur.getCodeUnique();
    }

/* chaque sous classe affiche ces propres informations */

    public abstract String afficher ();
}