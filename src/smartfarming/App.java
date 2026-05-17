package smartfarming;

import java.time.LocalDate;
import smartfarming.enums.StatusCapteur;
import smartfarming.enums.TypeMesure;
import smartfarming.models.LimitesGeographiques;
import smartfarming.models.ZoneCulture;
import smartfarming.models.ZoneElevage;
import smartfarming.models.hadil_code.*;

public class App {
   
    public static void main(String[] args) throws InterruptedException {

        StringBuilder sb = new StringBuilder();

        // =========================================
        // 1. Création des capteurs
        // =========================================
        sb.append("=== CREATION DES CAPTEURS ===\n\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        CapteurEnvironnemental captEnv = new CapteurEnvironnemental(
            StatusCapteur.ACTIF, new PlageSeuils(10, 40),
            TypeMesure.TEMPERATURE, "C");

        CapteurSol captSol = new CapteurSol(
            StatusCapteur.ACTIF, new PlageSeuils(5.5, 7.5),
            TypeMesure.PH, "pH");

        CapteurBiometrique captBio = new CapteurBiometrique(
            StatusCapteur.ACTIF, new PlageSeuils(37.0, 40.0),
            TypeMesure.TEMPERATURE, "C");

        CapteurEau captEau = new CapteurEau(
            StatusCapteur.ACTIF, new PlageSeuils(6.5, 8.5),
            TypeMesure.OXYGENE_DISSOUS, "mg/L");

        CapteurGPS captGPS = new CapteurGPS(StatusCapteur.ACTIF);

        // =========================================
        // 2. Création des zones + ajout capteurs
        // =========================================
        ZoneCulture zc = new ZoneCulture("ZC-001", "Zone Ble");
        ZoneElevage ze = new ZoneElevage("ZE-001", "Zone Vaches",
            new LimitesGeographiques(36.6, 36.9, 3.6, 3.9));

        zc.ajouterCapteur(captEnv);
        zc.ajouterCapteur(captSol);
        ze.ajouterCapteur(captBio);
        ze.ajouterCapteur(captGPS);

        sb.append(zc.toString()).append("\n");
        sb.append(ze.toString()).append("\n");
        System.out.println(sb.toString());
        sb.setLength(0);
        
        // =========================================
        // 3. Génération de relevés
        // =========================================
        sb.append("=== GENERATION DE RELEVES ===\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        // Générer plusieurs relevés
        for (int i = 0; i < 10; i++) {
            captEnv.envoyerReleve();
            captSol.envoyerReleve();
            captBio.envoyerReleve();
            captEau.envoyerReleve();
            captGPS.envoyerReleve();
            Thread.sleep(100);
        }

        sb.append("✅ Releves generés\n\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        // =========================================
        // 4. Envoi de relevés + affichage
        // =========================================
        sb.append("=== AFFICHAGE DES DERNIERS RELEVES ===\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        ReleveNumerique r1 = captEnv.envoyerReleve();
        ReleveNumerique r2 = captSol.envoyerReleve();
        ReleveNumerique r3 = captBio.envoyerReleve();
        ReleveNumerique r4 = captEau.envoyerReleve();
        ReleveGPS       r5 = captGPS.envoyerReleve();

        sb.append(r1.afficher()).append("\n");
        sb.append(r2.afficher()).append("\n");
        sb.append(r3.afficher()).append("\n");
        sb.append(r4.afficher()).append("\n");
        sb.append(r5.afficher()).append("\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        // =========================================
        // 5. Vérification des seuils
        // =========================================
        sb.append("=== VERIFICATION DES SEUILS ===\n");
        sb.append("ENV  hors seuil ? ")
          .append(r1.verifierSeuil(captEnv.getPlageSeuils())).append("\n");
        sb.append("SOL  hors seuil ? ")
          .append(r2.verifierSeuil(captSol.getPlageSeuils())).append("\n");
        sb.append("BIO  hors seuil ? ")
          .append(r3.verifierSeuil(captBio.getPlageSeuils())).append("\n");
        sb.append("EAU  hors seuil ? ")
          .append(r4.verifierSeuil(captEau.getPlageSeuils())).append("\n\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        // =========================================
        // 6. Suspension / Réactivation
        // =========================================
        sb.append("=== SUSPENSION / REACTIVATION ===\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        zc.suspendre();
        sb.append("Zone Ble suspendue ? ").append(zc.estSuspendu()).append("\n");
        sb.append("CapteurEnv statut : ").append(captEnv.getStatus()).append("\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        zc.reactiver();
        sb.append("Zone Ble reactivee ? ").append(!zc.estSuspendu()).append("\n");
        sb.append("CapteurEnv statut : ").append(captEnv.getStatus()).append("\n\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        // =========================================
        // 7. Graphiques ASCII
        // =========================================
        LocalDate aujourd = LocalDate.now();
        System.out.println("=== GRAPHIQUES ASCII ===");
        GraphiqueASCII.afficherCapteur(captEnv, aujourd, aujourd);
        GraphiqueASCII.afficherStatistiques(captEnv, aujourd, aujourd);

        // =========================================
        // 8. Tableau de bord
        // =========================================
        System.out.println("\n=== TABLEAU DE BORD ===");
        TableauDeBord tableau = new TableauDeBord();
        tableau.ajouterZone(zc);
        tableau.ajouterZone(ze);
        tableau.afficher();
    }
}