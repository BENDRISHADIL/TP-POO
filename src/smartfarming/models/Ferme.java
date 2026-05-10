package smartfarming.models;

import java.util.List;
import java.util.ArrayList;

public class Ferme {

    private String nom;
    private String localisation;
    private List<Zone> zones;

    public Ferme(String nom, String localisation) {
        this.nom = nom;
        this.localisation = localisation;
        this.zones = new ArrayList<>();
    }

    // Add a zone to the farm
    public void ajouterZone(Zone zone) {
        this.zones.add(zone);
    }

    // Remove a zone
    public void supprimerZone(Zone zone) {
        this.zones.remove(zone);
    }

    // Get all zones
    public List<Zone> getZones() { return zones; }

    // Get zones by type
    public List<ZoneCulture> getZonesCulture() {
        List<ZoneCulture> result = new ArrayList<>();
        for (Zone zone : zones) {
            if (zone instanceof ZoneCulture) {
                result.add((ZoneCulture) zone);
            }
        }
        return result;
    }

    public List<ZoneElevage> getZonesElevage() {
        List<ZoneElevage> result = new ArrayList<>();
        for (Zone zone : zones) {
            if (zone instanceof ZoneElevage) {
                result.add((ZoneElevage) zone);
            }
        }
        return result;
    }

    public List<ZoneAquacole> getZonesAquacole() {
        List<ZoneAquacole> result = new ArrayList<>();
        for (Zone zone : zones) {
            if (zone instanceof ZoneAquacole) {
                result.add((ZoneAquacole) zone);
            }
        }
        return result;
    }

    // Getters
    public String getNom() { return nom; }
    public String getLocalisation() { return localisation; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ferme: ").append(nom).append("\n");
        sb.append("Localisation: ").append(localisation).append("\n");
        sb.append("Nombre de zones: ").append(zones.size());
        return sb.toString();
    }
    public String afficherProgrammesAlimentation() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== Programmes Alimentation par Zone ===\n\n");
    for (ZoneElevage zone : getZonesElevage()) {
        sb.append("Zone: ").append(zone.getNom()).append("\n");
        sb.append(zone.afficherProgrammeAlimentation()).append("\n");
        sb.append("-------------------\n");
    }
    return sb.toString();
    }
    public String afficherVueEnsemble() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== Ferme: ").append(nom).append(" ===\n");
    sb.append("Nombre total de zones: ").append(zones.size()).append("\n\n");
    for (Zone zone : zones) {
        sb.append(zone.toString()).append("\n");
        sb.append("-------------------\n");
    }// afficher le nombres d'entites hébergées
    return sb.toString();
}
}