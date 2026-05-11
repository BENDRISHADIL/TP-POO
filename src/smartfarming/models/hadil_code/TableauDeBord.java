package smartfarming.models.hadil_code;  

import java.util.ArrayList;
import java.util.List;
import smartfarming.models.Zone;
public class TableauDeBord {
   // Codes couleur ANSI pour le terminal
    private static final String VERT   = "\u001B[32m";  // NORMAL
    private static final String JAUNE  = "\u001B[33m";  // AVERTISSEMENT
    private static final String ROUGE  = "\u001B[31m";  // CRITIQUE
    private static final String RESET  = "\u001B[0m";   // reset couleur

    private List<Zone> zones;
    
    public TableauDeBord() {
        this.zones = new ArrayList<>();
    } 
    /** Ajoute une zone au tableau de bord */
    public void ajouterZone(Zone zone) {
        zones.add(zone);
    } 
    /**
     * Détermine le niveau selon la valeur et les seuils :
     * - CRITIQUE      : hors seuils
     * - AVERTISSEMENT : dans les 10% des limites
     * - NORMAL        : bien dans la plage
     */
    private String determinerNiveau(double valeur, PlageSeuils ps) {
        double min = ps.getValeurMin();
        double max = ps.getValeurMax();
        double marge = (max - min) * 0.10; // 10% de marge

        if (valeur < min || valeur > max) {
            return "CRITIQUE";
        } else if (valeur < min + marge || valeur > max - marge) {
            return "AVERTISSEMENT";
        } else {
            return "NORMAL";
        }
    }

    /** Retourne la couleur selon le niveau */
    private String couleur(String niveau) {
        switch (niveau) {
            case "CRITIQUE":       return ROUGE;
            case "AVERTISSEMENT":  return JAUNE;
            default:               return VERT;
        }
    }

    /**
     * Affiche le tableau de bord complet :
     * une section par zone, un relevé par capteur
     */
    public void afficher() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔══════════════════════════════════════════╗\n");
        sb.append(  "║         TABLEAU DE BORD - FERME           ║\n");
        sb.append(  "╚══════════════════════════════════════════╝\n");

        for (Zone zone : zones) {
            sb.append("\n┌─────────────────────────────────────────\n");
            sb.append("│ ZONE : ").append(zone.getNom())
              .append(" [").append(zone.getCode()).append("]")
              .append(" | Statut : ").append(zone.getStatut()).append("\n");
            sb.append("├─────────────────────────────────────────\n");

            // Si zone suspendue  pas de relevés
            if (zone.estSuspendu()) {
                sb.append("│   Zone suspendue — aucun relevé\n");
                sb.append("└─────────────────────────────────────────\n");
                System.out.print(sb.toString());
                sb.setLength(0);
                continue;
            }

            // Pas de capteurs dans la zone
            if (zone.getCapteurs().isEmpty()) {
                sb.append("│  Aucun capteur configuré dans cette zone\n");
                sb.append("└─────────────────────────────────────────\n");
                System.out.print(sb.toString());
                sb.setLength(0);
                continue;
            }

            // Affichage de chaque capteur
            for (Capteur capteur : zone.getCapteurs()) {

                sb.append("│  Capteur : ").append(capteur.getCodeUnique())
                  .append(" | ").append(capteur.getStatus()).append("\n");

                // Capteur suspendu  pas de relevé
                if (capteur.getStatus() == smartfarming.enums.StatusCapteur.SUSPENDU) {
                    sb.append("│    -> Suspendu\n");
                    continue;
                }

                // Envoi du relevé
                Releve releve = capteur.envoyerReleve();

                // Relevé numérique → affichage avec couleur
                if (releve instanceof ReleveNumerique) {
                    ReleveNumerique rn = (ReleveNumerique) releve;
                    String niveau = determinerNiveau(
                        rn.getValeur(),
                        capteur.getPlageSeuils()
                    );
                    String couleur = couleur(niveau);

                    // On affiche le StringBuilder AVANT la couleur
                    System.out.print(sb.toString());
                    sb.setLength(0);

                    // Affichage coloré
                    System.out.println("│    -> Valeur : "
                        + couleur + rn.getValeur() + " " + rn.getUnite()
                        + "  [" + niveau + "]" + RESET);

                // Relevé GPS
                } else if (releve instanceof ReleveGPS) {
                    ReleveGPS rg = (ReleveGPS) releve;
                    sb.append("│    -> GPS : lat=").append(rg.getLatitude())
                      .append(", lon=").append(rg.getLongitude()).append("\n");
                }
            }
            sb.append("└─────────────────────────────────────────\n");
            System.out.print(sb.toString());
            sb.setLength(0);
        }
    }
}

