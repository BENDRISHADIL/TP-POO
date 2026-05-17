package smartfarming.models.hadil_code;

import java.time.LocalDateTime;

/**
 * Classe abstraite représentant un relevé émis par un capteur.
 * Tout relevé a une date/heure et un capteur source.
 */
public abstract class Releve {

    private LocalDateTime dateHeure;
    private Capteur capteur;

    /**
     * Constructeur — horodaté automatiquement à la création.
     * 
     * @param capteur le capteur qui a émis ce relevé
     */
    public Releve(Capteur capteur) {
        this.capteur = capteur;
        this.dateHeure = LocalDateTime.now();  // enregistre la date/heure de création
    }

    //  Retourne LocalDateTime (pas String)
    public LocalDateTime getHorodatage() {
        return dateHeure;
    }

    //  Garde aussi cette méthode pour compatibilité
    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public Capteur getCapteur() {
        return capteur;
    }

    public String getCodeCapteur() {
        return capteur.getCodeUnique();
    }

    /**
     * Méthode abstraite : chaque sous-classe affiche ses propres données.
     */
    public abstract String afficher();

    @Override
    public String toString() {
        return afficher();
    }
}