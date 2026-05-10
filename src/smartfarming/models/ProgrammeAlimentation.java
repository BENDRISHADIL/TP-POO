package smartfarming.models;

import java.util.List;
import java.util.ArrayList;

public class ProgrammeAlimentation {

    private String nom;
    private List<String> repas;
    private int nombreRepasParJour;

    public ProgrammeAlimentation(String nom, int nombreRepasParJour) {
        this.nom = nom;
        this.nombreRepasParJour = nombreRepasParJour;
        this.repas = new ArrayList<>();
    }

    // Add a meal to the program
    public void ajouterRepas(String repas) {
        this.repas.add(repas);
    }

    // Getters
    public String getNom() { return nom; }
    public int getNombreRepasParJour() { return nombreRepasParJour; }
    public List<String> getRepas() { return repas; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Programme: ").append(nom).append("\n");
        sb.append("Repas par jour: ").append(nombreRepasParJour).append("\n");
        sb.append("Repas: ");
        for (String repas : this.repas) {
            sb.append("\n  - ").append(repas);
        }
        return sb.toString();
    }
}