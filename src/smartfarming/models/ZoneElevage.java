package smartfarming.models;

import java.util.List;
import java.util.ArrayList;

public class ZoneElevage extends Zone {

    private List<Animal> animaux;
    private ProgrammeAlimentation programmeAlimentation;
    private LimitesGeographiques limites;

    public ZoneElevage(String code, String nom, LimitesGeographiques limites) {
        super(code, nom);
        this.animaux = new ArrayList<>();
        this.limites = limites;
    }

    public void ajouterAnimal(Animal animal) {
        this.animaux.add(animal);
    }

    public void definirProgrammeAlimentation(ProgrammeAlimentation programme) {
        this.programmeAlimentation = programme;
    }

    public String afficherProgrammeAlimentation() {
        if (programmeAlimentation == null) {
            return "Aucun programme defini pour cette zone";
        }
        return programmeAlimentation.toString();
    }

    public List<Animal> getAnimaux() { return animaux; }
    public ProgrammeAlimentation getProgrammeAlimentation() { return programmeAlimentation; }
    public LimitesGeographiques getLimites() { return limites; }

    @Override
    public int getNombreEntites() {
        return animaux.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Zone Elevage: ").append(getNom()).append(" [").append(getCode()).append("]\n");
        sb.append("Statut: ").append(getStatut()).append("\n");
        sb.append("Nombre d'animaux: ").append(animaux.size());
        return sb.toString();
    }
}