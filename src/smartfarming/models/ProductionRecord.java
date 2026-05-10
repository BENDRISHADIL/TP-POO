package smartfarming.models;

import smartfarming.enums.TypeProduction;
import java.time.LocalDate;

public class ProductionRecord {

    private TypeProduction typeProduction;
    private double quantite;
    private String unite;
    private LocalDate date;

    public ProductionRecord(TypeProduction typeProduction, double quantite, String unite, LocalDate date) {
        this.typeProduction = typeProduction;
        this.quantite = quantite;
        this.unite = unite;
        this.date = date;
    }

    public TypeProduction getTypeProduction() { return typeProduction; }
    public double getQuantite() { return quantite; }
    public String getUnite() { return unite; }
    public LocalDate getDate() { return date; }
}