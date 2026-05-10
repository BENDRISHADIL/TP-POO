package out.smartfarming.models.hadil_code;

import java.time.LocalDateTime;
public abstract class Releve {
    private LocalDateTime dateHeure;
    private Capteur capteur;

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
/* chaque sous classe affiche ces propres informations */

    public abstract String AfficherReleve ();
}
