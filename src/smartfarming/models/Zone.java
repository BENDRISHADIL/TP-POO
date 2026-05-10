package smartfarming.models;

import smartfarming.enums.StatutZone;
import smartfarming.interfaces.Suspendable;
import java.util.List;
import java.util.ArrayList;

public abstract class Zone implements Suspendable {

    private String code;
    private String nom;
    private StatutZone statut;
    private List<ProductionRecord> productions;
    private List<String> capteurs;

    public Zone(String code, String nom) {
        this.code = code;
        this.nom = nom;
        this.statut = StatutZone.ACTIVE;
        this.productions = new ArrayList<>();
        this.capteurs = new ArrayList<>();
    }

    public abstract int getNombreEntites();

    public String getCode() { return code; }
    public String getNom() { return nom; }
    public StatutZone getStatut() { return statut; }
    public List<ProductionRecord> getProductions() { return productions; }
    public List<String> getCapteurs() { return capteurs; }

    public void setNom(String nom) { this.nom = nom; }

    public void enregistrerProduction(ProductionRecord production) {
        this.productions.add(production);
    }

    public void ajouterCapteur(String capteur) {
        this.capteurs.add(capteur);
    }

    @Override
    public void suspendre() {
        this.statut = StatutZone.SUSPENDUE;
    }

    @Override
    public void reactiver() {
        this.statut = StatutZone.ACTIVE;
    }

    @Override
    public boolean estSuspendu() {
        return this.statut == StatutZone.SUSPENDUE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Zone: ").append(nom).append(" [").append(code).append("]\n");
        sb.append("Statut: ").append(statut).append("\n");
        sb.append("Nombre d'entites: ").append(getNombreEntites()).append("\n");
        sb.append("Productions: ").append(productions.size());
        return sb.toString();
    }
}