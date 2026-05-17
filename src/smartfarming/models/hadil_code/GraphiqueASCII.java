package smartfarming.models.hadil_code;

import smartfarming.models.Zone;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Affiche des graphiques ASCII dans le terminal.
 */
public class GraphiqueASCII {

    private static final String VERT   = "\u001B[32m";
    private static final String JAUNE  = "\u001B[33m";
    private static final String BLEU   = "\u001B[34m";
    private static final String ROUGE  = "\u001B[31m";
    private static final String RESET  = "\u001B[0m";

    /**
     * Affiche un graphique ASCII pour un capteur.
     */
    public static void afficherCapteur(Capteur capteur, LocalDate debut, LocalDate fin) {
        
        // ✅ CORRIGE — utilise consulterHistorique (pas ...Detaille)
        List<Releve> releves = capteur.consulterHistorique(debut, fin);
        
        if (releves.isEmpty()) {
            System.out.println(ROUGE + "Aucun releve pour cette periode." + RESET);
            return;
        }

        StringBuilder sb = new StringBuilder();
        
        // En-tete
        sb.append("\n").append(BLEU);
        sb.append("╔════════════════════════════════════════╗\n");
        sb.append("║ GRAPHIQUE - ").append(capteur.getCodeUnique()).append("\n");
        sb.append("║ Du ").append(debut).append(" au ").append(fin).append("\n");
        sb.append("╚════════════════════════════════════════╝\n");
        sb.append(RESET);

        // Extraire les valeurs numeriques
        double[] valeurs = new double[releves.size()];
        String[] heures = new String[releves.size()];
        
        int index = 0;
        double minValeur = Double.MAX_VALUE;
        double maxValeur = Double.MIN_VALUE;

        for (Releve r : releves) {
            if (r instanceof ReleveNumerique) {
                ReleveNumerique rn = (ReleveNumerique) r;
                double valeur = rn.getValeur();
                
                valeurs[index] = valeur;
                //  CORRIGE — utilise getHorodatage() (pas getDateHeure())
                LocalDateTime dateTime = r.getHorodatage();
                heures[index] = String.format("%02d:%02d", 
                    dateTime.getHour(), dateTime.getMinute());

                if (valeur < minValeur) minValeur = valeur;
                if (valeur > maxValeur) maxValeur = valeur;
                index++;
            }
        }

        if (index == 0) {
            System.out.println(ROUGE + "Aucun releve numerique pour cette periode." + RESET);
            return;
        }

        // Trouver min et max avec marge
        double marge = (maxValeur - minValeur) * 0.1;
        double min = minValeur - marge;
        double max = maxValeur + marge;

        // Hauteur du graphique
        int hauteur = 15;

        // Afficher le graphique ligne par ligne
        for (int ligne = hauteur; ligne >= 0; ligne--) {
            // Echelle Y
            double valeurLigne = min + (max - min) * ligne / hauteur;
            sb.append(String.format("%6.1f │ ", valeurLigne));

            // Points du graphique
            for (int col = 0; col < index; col++) {
                double positionNormalisee = (valeurs[col] - min) / (max - min);
                int hauteurPoint = (int) (positionNormalisee * hauteur);

                if (hauteurPoint == ligne) {
                    // Point exact
                    String couleur = determinerCouleur(valeurs[col], minValeur, maxValeur);
                    sb.append(couleur).append("*").append(RESET).append(" ");
                } else if (hauteurPoint > ligne && hauteurPoint - 1 <= ligne) {
                    // Trait vertical
                    sb.append(BLEU).append("│").append(RESET).append(" ");
                } else {
                    // Vide
                    sb.append("  ");
                }
            }
            sb.append("\n");
        }

        // Axe X (horizontal)
        sb.append("       └");
        for (int i = 0; i < index; i++) {
            sb.append("──");
        }
        sb.append("\n");

        // Labels temps
        sb.append("        ");
        for (String h : heures) {
            sb.append(h.substring(0, 1)).append(" ");
        }
        sb.append("\n");

        // Legende
        sb.append(BLEU).append("\nLegende :\n");
        sb.append(VERT).append("  * ").append(RESET).append("= Normal\n");
        sb.append(JAUNE).append("  * ").append(RESET).append("= Avertissement\n");
        sb.append(ROUGE).append("  * ").append(RESET).append("= Critique\n");
        sb.append(RESET);

        System.out.println(sb.toString());
    }

    /**
     * Determine la couleur selon la valeur et les seuils.
     */
    private static String determinerCouleur(double valeur, double min, double max) {
        double marge = (max - min) * 0.1;

        if (valeur < min + marge || valeur > max - marge) {
            return JAUNE;
        }
        return VERT;
    }

    /**
     * Affiche un graphique pour tous les capteurs d'une zone.
     */
    public static void afficherZone(Zone zone, LocalDate debut, LocalDate fin) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(BLEU);
        sb.append("╔════════════════════════════════════════╗\n");
        sb.append("║ GRAPHIQUE ZONE - ").append(zone.getNom()).append("\n");
        sb.append("║ Du ").append(debut).append(" au ").append(fin).append("\n");
        sb.append("╚════════════════════════════════════════╝\n");
        sb.append(RESET);
        System.out.println(sb.toString());

        // Afficher graphique pour chaque capteur
        for (Capteur c : zone.getCapteurs()) {
            afficherCapteur(c, debut, fin);
        }
    }

    /**
     * Affiche des statistiques simples.
     */
    public static void afficherStatistiques(Capteur capteur, LocalDate debut, LocalDate fin) {
        
        // ✅ CORRIGE — utilise consulterHistorique
        List<Releve> releves = capteur.consulterHistorique(debut, fin);
        
        if (releves.isEmpty()) {
            System.out.println(ROUGE + "Aucun releve pour cette periode." + RESET);
            return;
        }

        double somme = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        int count = 0;

        for (Releve r : releves) {
            if (r instanceof ReleveNumerique) {
                ReleveNumerique rn = (ReleveNumerique) r;
                double valeur = rn.getValeur();
                somme += valeur;
                if (valeur < min) min = valeur;
                if (valeur > max) max = valeur;
                count++;
            }
        }

        double moyenne = somme / count;

        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(BLEU);
        sb.append("╔════════════════════════════════════════╗\n");
        sb.append("║ STATISTIQUES - ").append(capteur.getCodeUnique()).append("\n");
        sb.append("╠════════════════════════════════════════╣\n");
        sb.append("║ Minimum       : ").append(String.format("%.2f", min)).append("\n");
        sb.append("║ Maximum       : ").append(String.format("%.2f", max)).append("\n");
        sb.append("║ Moyenne       : ").append(String.format("%.2f", moyenne)).append("\n");
        sb.append("║ Ecart         : ").append(String.format("%.2f", max - min)).append("\n");
        sb.append("║ Mesures       : ").append(count).append("\n");
        sb.append("╚════════════════════════════════════════╝\n");
        sb.append(RESET);
        System.out.println(sb.toString());
    }
}