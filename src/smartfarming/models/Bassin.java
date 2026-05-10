package smartfarming.models;

import java.util.List;
import java.util.ArrayList;

public class Bassin {

    private String code;
    private double capacite;
    private List<String> especesAquatiques;

    public Bassin(String code, double capacite) {
        this.code = code;
        this.capacite = capacite;
        this.especesAquatiques = new ArrayList<>();
    }

    // Add aquatic species
    public void ajouterEspece(String espece) {
        this.especesAquatiques.add(espece);
    }

    // Getters
    public String getCode() { return code; }
    public double getCapacite() { return capacite; }
    public List<String> getEspecesAquatiques() { return especesAquatiques; }
}