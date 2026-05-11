package smartfarming; 
import smartfarming.enums.StatusCapteur;
import smartfarming.enums.TypeMesure;
import smartfarming.models.LimitesGeographiques;
import smartfarming.models.ZoneCulture;
import smartfarming.models.ZoneElevage;
import smartfarming.models.hadil_code.CapteurBiometrique;
import smartfarming.models.hadil_code.CapteurEau;
import smartfarming.models.hadil_code.CapteurEnvironnemental;
import smartfarming.models.hadil_code.CapteurGPS;
import smartfarming.models.hadil_code.CapteurSol;
import smartfarming.models.hadil_code.PlageSeuils;
import smartfarming.models.hadil_code.ReleveGPS;
import smartfarming.models.hadil_code.ReleveNumerique;
import smartfarming.models.hadil_code.TableauDeBord;

public class App {
   
    public static void main(String[] args) {


        StringBuilder sb = new StringBuilder();

        // =========================================
        // 1. Création des capteurs
        // =========================================
        sb.append("=== TEST DES CAPTEURS ===\n\n");
        System.out.println(sb.toString());
        sb.setLength(0); // réinitialise le StringBuilder

        CapteurEnvironnemental captEnv = new CapteurEnvironnemental(StatusCapteur.ACTIF,new PlageSeuils(10, 40),TypeMesure.TEMPERATURE,"°C");

        CapteurSol captSol = new CapteurSol(StatusCapteur.ACTIF,new PlageSeuils(5.5, 7.5),TypeMesure.PH,"pH");

        CapteurBiometrique captBio = new CapteurBiometrique(StatusCapteur.ACTIF,new PlageSeuils(37.0, 40.0),TypeMesure.TEMPERATURE,"°C");

        CapteurEau captEau = new CapteurEau(StatusCapteur.ACTIF,new PlageSeuils(6.5, 8.5),TypeMesure.PH, "pH");

        CapteurGPS captGPS = new CapteurGPS(StatusCapteur.ACTIF);

        // =========================================
        // 2. Création des zones + ajout capteurs
        // =========================================


        //  Ajouter des zones
        ZoneCulture zc = new ZoneCulture("ZC-001", "Zone Ble");
        ZoneElevage ze = new ZoneElevage("ZE-001", "Zone Vaches", new LimitesGeographiques(36.6, 36.9, 3.6, 3.9));

        zc.ajouterCapteur(captEnv);
        zc.ajouterCapteur(captSol);
        ze.ajouterCapteur(captBio);
        ze.ajouterCapteur(captGPS);

        sb.append(zc.toString()).append("\n");
        sb.append(ze.toString()).append("\n");
        System.out.println(sb.toString());
        sb.setLength(0);
        
        // =========================================
        // 2. Envoi de relevés + affichage
        // =========================================
        sb.append("--- Envoi des relevés ---\n");
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
        // 4. Vérification des seuils
        // =========================================
        sb.append("______________  VERIFICATION DES SEUILS____________\n");
        sb.append("ENV  hors seuil = ")
          .append(r1.verifierSeuil(captEnv.getPlageSeuils())).append("\n");
        sb.append("SOL  hors seuil = ")
          .append(r2.verifierSeuil(captSol.getPlageSeuils())).append("\n");
        sb.append("BIO  hors seuil = ")
          .append(r3.verifierSeuil(captBio.getPlageSeuils())).append("\n");
        sb.append("EAU  hors seuil = ")
          .append(r4.verifierSeuil(captEau.getPlageSeuils())).append("\n");
        System.out.println(sb.toString());
        sb.setLength(0);
       // =========================================
        // 5. Suspension / Réactivation
        // =========================================
        sb.append("__________ SUSPENSION / REACTIVATION _____________\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        zc.suspendre();
        captEnv.suspendre();
        sb.append("Zone Ble suspendue = ").append(zc.estSuspendu()).append("\n");
        sb.append("CapteurEnv statut  : ").append(captEnv.getStatus()).append("\n");
        System.out.println(sb.toString());
        sb.setLength(0);
        

        zc.reactiver();
        captEnv.reactiver();
        sb.append("Zone Ble reactivee = ").append(!zc.estSuspendu()).append("\n");
       // sb.append("CapteurEnv suspendu = ").append(captEnv.estSuspendu()).append("\n");
        sb.append("CapteurEnv statut   : ").append(captEnv.getStatus()).append("\n");
        System.out.println(sb.toString());
        sb.setLength(0);
        



        // =========================================
        // 6. Tableau de bord
        // =========================================
        sb.append("__________ TABLEAU DE BORD___________\n");
        System.out.println(sb.toString());
        sb.setLength(0);

        TableauDeBord tableau = new TableauDeBord();
        tableau.ajouterZone(zc);
        tableau.ajouterZone(ze);
        tableau.afficher();
    }
}