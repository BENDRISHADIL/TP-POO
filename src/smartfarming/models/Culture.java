package smartfarming.models;

import smartfarming.enums.FamilleCulture;
import smartfarming.enums.StadeCroissance;
import java.time.LocalDate;

public class Culture {

    private String nom;
    private FamilleCulture famille;
    private LocalDate datePlantation;
    private LocalDate dateRecolte;
    private StadeCroissance stadeCroissance;
    private ExigencePedologique exigencePedologique;

    public Culture(String nom, FamilleCulture famille,
                   LocalDate datePlantation, LocalDate dateRecolte,
                   ExigencePedologique exigencePedologique) {
        this.nom = nom;
        this.famille = famille;
        this.datePlantation = datePlantation;
        this.dateRecolte = dateRecolte;
        this.exigencePedologique = exigencePedologique;
        this.stadeCroissance = StadeCroissance.SEMIS;
    }

    // Update growth stage
    public void mettreAJourStade(StadeCroissance nouveauStade) {
        this.stadeCroissance = nouveauStade;
    }

    // Getters
    public String getNom() { return nom; }
    public FamilleCulture getFamille() { return famille; }
    public LocalDate getDatePlantation() { return datePlantation; }
    public LocalDate getDateRecolte() { return dateRecolte; }
    public StadeCroissance getStadeCroissance() { return stadeCroissance; }
    public ExigencePedologique getExigencePedologique() { return exigencePedologique; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Culture: ").append(nom).append("\n");
        sb.append("Famille: ").append(famille).append("\n");
        sb.append("Stade: ").append(stadeCroissance).append("\n");
        sb.append("Plantation: ").append(datePlantation).append("\n");
        sb.append("Recolte prevue: ").append(dateRecolte);
        return sb.toString();
    }
}