import smartfarming.enums.*;
import smartfarming.models.*;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        // =============================
        // CREATE THE FARM
        // =============================
        Ferme ferme = new Ferme("Ferme ESI", "Alger");

        // =============================
        // FUNCTION 1 — GERER LES ZONES
        // =============================

        // 1.1 Ajouter des zones
        ZoneCulture zoneCulture = new ZoneCulture("ZC-001", "Zone Ble");
        ZoneElevage zoneElevage = new ZoneElevage("ZE-001", "Zone Vaches", 
            new LimitesGeographiques(36.6, 36.9, 3.6, 3.9));
        ZoneAquacole zoneAquacole = new ZoneAquacole("ZA-001", "Zone Poissons");

        ferme.ajouterZone(zoneCulture);
        ferme.ajouterZone(zoneElevage);
        ferme.ajouterZone(zoneAquacole);
        System.out.println("=== 1.1 Zones ajoutees ===");
        System.out.println(ferme.toString());

        // 1.2 Modifier une zone
        zoneElevage.setNom("Zone Vaches Modifiee");
        System.out.println("\n=== 1.2 Zone modifiee ===");
        System.out.println("Nouveau nom: " + zoneElevage.getNom());

        // 1.3 Suspendre et reactiver une zone
        System.out.println("\n=== 1.3 Suspendre/Reactiver ===");
        zoneCulture.suspendre();
        System.out.println("Zone Culture suspendue: " + zoneCulture.estSuspendu());
        zoneCulture.reactiver();
        System.out.println("Zone Culture reactivee: " + !zoneCulture.estSuspendu());

        // 1.4 Afficher vue ensemble et le nombres d'entites hébergées
        System.out.println("\n=== 1.4 Vue ensemble ===");
        System.out.println(ferme.afficherVueEnsemble());

        // 1.5 Enregistrer production
        System.out.println("=== 1.5 Productions ===");
        ProductionRecord productionCulture = new ProductionRecord(TypeProduction.RENDEMENT_CULTURE, 200.0, "kg", LocalDate.now() );
        ProductionRecord productionElevage = new ProductionRecord( TypeProduction.LAIT, 50.0, "litres", LocalDate.now()
        );
        ProductionRecord productionAquacole = new ProductionRecord(TypeProduction.POIDS_RECOLTE, 150.0, "kg", LocalDate.now()
        );
        zoneCulture.enregistrerProduction(productionCulture);
        zoneElevage.enregistrerProduction(productionElevage);
        zoneAquacole.enregistrerProduction(productionAquacole);
        System.out.println("Productions enregistrees: ");
        System.out.println("Zone Culture: " + zoneCulture.getProductions().size() + " production(s)");
        System.out.println("Zone Elevage: " + zoneElevage.getProductions().size() + " production(s)");
        System.out.println("Zone Aquacole: " + zoneAquacole.getProductions().size() + " production(s)");

        // =============================
        // FUNCTION 2 — GERER LES CULTURES
        // =============================

        // 2.1 Enregistrer une culture
        System.out.println("\n=== 2.1 Enregistrer culture ===");
        ExigencePedologique exigence = new ExigencePedologique(
            6.0, 7.5, 30.0, 60.0, 10.0, 30.0
        );
        Culture ble = new Culture(
            "Ble dur", FamilleCulture.CEREALE,
            LocalDate.of(2026, 1, 10),
            LocalDate.of(2026, 6, 20),
            exigence
        );
        Culture tomate = new Culture(
            "Tomate", FamilleCulture.LEGUME,
            LocalDate.of(2026, 2, 1),
            LocalDate.of(2026, 5, 30),
            exigence
        );
        zoneCulture.ajouterCulture(ble);
        zoneCulture.ajouterCulture(tomate);
        System.out.println("Cultures ajoutees: " + zoneCulture.getNombreEntites());

        // 2.2 Mettre a jour stade de croissance
        System.out.println("\n=== 2.2 Mise a jour stade ===");
        System.out.println("Stade initial ble: " + ble.getStadeCroissance());
        ble.mettreAJourStade(StadeCroissance.CROISSANCE);
        System.out.println("Stade apres MAJ: " + ble.getStadeCroissance());

        // 2.3 Generer rapport statut par zone
        System.out.println("\n=== 2.3 Rapport statut cultures ===");
        System.out.println(zoneCulture.genererRapportStatut());

        // =============================
        // FUNCTION 3 — GERER LES ANIMAUX
        // =============================

        // 3.1 Enregistrer des animaux
        System.out.println("=== 3.1 Enregistrer animaux ===");
        Animal vache1 = new Animal(
            "V-001", "Vache Holstein", 36, 550.0, TypeElevage.RUMINANT
        );
        Animal vache2 = new Animal(
            "V-002", "Vache Montbeliarde", 48, 600.0, TypeElevage.RUMINANT
        );
        Animal poulet1 = new Animal(
            "P-001", "Poulet", 6, 2.5, TypeElevage.VOLAILLE
        );
        zoneElevage.ajouterAnimal(vache1);
        zoneElevage.ajouterAnimal(vache2);
        zoneElevage.ajouterAnimal(poulet1);
        System.out.println("Animaux enregistres: " + zoneElevage.getNombreEntites());
        System.out.println(vache1.toString());

        // 3.2 Enregistrer evenements sanitaires
        System.out.println("\n=== 3.2 Evenements sanitaires ===");
        vache1.enregistrerMaladie("Fievre aphteuse", LocalDate.now());
        System.out.println("Etat sante vache1 apres maladie: " + vache1.getEtatSante());
        System.out.println("Nombre evenements vache1: " + vache1.getEvenements().size());

        vache2.enregistrerEvolutionPoids(620.0, LocalDate.now());
        System.out.println("Nouveau poids vache2: " + vache2.getPoids() + " kg");
        System.out.println("Nombre evenements vache2: " + vache2.getEvenements().size());

        // 3.3 Definir et afficher programme alimentation
        System.out.println("\n=== 3.3 Programme alimentation ===");
        ProgrammeAlimentation programme = new ProgrammeAlimentation("Programme Standard", 3);
        programme.ajouterRepas("Foin 5kg");
        programme.ajouterRepas("Concentre 2kg");
        programme.ajouterRepas("Eau 20L");
        zoneElevage.definirProgrammeAlimentation(programme);
        System.out.println(ferme.afficherProgrammesAlimentation());

        // =============================
        // ZONE AQUACOLE
        // =============================
        System.out.println("=== Zone Aquacole ===");
        Bassin bassin1 = new Bassin("B-001", 5000.0);
        bassin1.ajouterEspece("Tilapia");
        bassin1.ajouterEspece("Carpe");
        Bassin bassin2 = new Bassin("B-002", 3000.0);
        bassin2.ajouterEspece("Truite");
        bassin2.ajouterEspece("Saumon");
        zoneAquacole.ajouterBassin(bassin1);
        zoneAquacole.ajouterBassin(bassin2);
        System.out.println("Nombre de bassins: " + zoneAquacole.getBassins().size());
        System.out.println("Nombre total especes: " + zoneAquacole.getNombreEntites());
    }
}