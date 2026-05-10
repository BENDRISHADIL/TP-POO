package smartfarming.models;

import smartfarming.enums.TypeEvenementSanitaire;
import java.time.LocalDate;

public class EvenementSanitaire {

    private TypeEvenementSanitaire type;
    private LocalDate date;
    
    // Used only when type is MALADIE
    private String description;
    
    // Used only when type is EVOLUTION_POIDS
    private double ancienPoids;
    private double nouveauPoids;

    // Constructor for MALADIE
    public EvenementSanitaire(LocalDate date, String description) {
        this.type = TypeEvenementSanitaire.MALADIE;
        this.date = date;
        this.description = description;
    }

    // Constructor for EVOLUTION_POIDS
    public EvenementSanitaire(LocalDate date, double ancienPoids, double nouveauPoids) {
        this.type = TypeEvenementSanitaire.EVOLUTION_POIDS;
        this.date = date;
        this.ancienPoids = ancienPoids;
        this.nouveauPoids = nouveauPoids;
    }

    // Getters
    public TypeEvenementSanitaire getType() { return type; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public double getAncienPoids() { return ancienPoids; }
    public double getNouveauPoids() { return nouveauPoids; }
}