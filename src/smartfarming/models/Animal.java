package smartfarming.models;

import smartfarming.enums.TypeElevage;
import smartfarming.enums.EtatSante;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Animal {

    private String identifiant;
    private String espece;
    private int age;
    private double poids;
    private EtatSante etatSante;
    private TypeElevage typeElevage;
    private List<EvenementSanitaire> evenements;

    public Animal(String identifiant, String espece, int age,
                  double poids, TypeElevage typeElevage) {
        this.identifiant = identifiant;
        this.espece = espece;
        this.age = age;
        this.poids = poids;
        this.typeElevage = typeElevage;
        this.etatSante = EtatSante.SAIN;
        this.evenements = new ArrayList<>();
    }

    // Record disease
    public void enregistrerMaladie(String description, LocalDate date) {
        EvenementSanitaire evenement = new EvenementSanitaire(date, description);
        this.etatSante = EtatSante.MALADE;
        this.evenements.add(evenement);
    }

    // Record weight change
    public void enregistrerEvolutionPoids(double nouveauPoids, LocalDate date) {
        EvenementSanitaire evenement = new EvenementSanitaire(date, this.poids, nouveauPoids);
        this.poids = nouveauPoids;
        this.evenements.add(evenement);
    }

    // Update health status
    public void mettreAJourEtatSante(EtatSante nouvelEtat) {
        this.etatSante = nouvelEtat;
    }

    // Getters
    public String getIdentifiant() { return identifiant; }
    public String getEspece() { return espece; }
    public int getAge() { return age; }
    public double getPoids() { return poids; }
    public EtatSante getEtatSante() { return etatSante; }
    public TypeElevage getTypeElevage() { return typeElevage; }
    public List<EvenementSanitaire> getEvenements() { return evenements; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Animal: ").append(espece).append(" [").append(identifiant).append("]\n");
        sb.append("Type: ").append(typeElevage).append("\n");
        sb.append("Age: ").append(age).append(" mois\n");
        sb.append("Poids: ").append(poids).append(" kg\n");
        sb.append("Etat de sante: ").append(etatSante);
        return sb.toString();
    }
}