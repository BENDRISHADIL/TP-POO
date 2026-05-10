package smartfarming.models;

import java.util.List;
import java.util.ArrayList;

public class ZoneCulture extends Zone {

    private List<Culture> cultures;

   public ZoneCulture(String code, String nom) {
    super(code, nom);
    this.cultures = new ArrayList<>();
}
    // Add a culture to this zone
    public void ajouterCulture(Culture culture) {
        this.cultures.add(culture);
    }

    // Get all cultures
    public List<Culture> getCultures() { return cultures; }

    // Count entities → number of cultures
    @Override
    public int getNombreEntites() {
        return cultures.size();
    }
    public String genererRapportStatut() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== Rapport Statut Cultures: ").append(getNom()).append(" ===\n\n");
    for (Culture culture : cultures) {
        sb.append("Culture: ").append(culture.getNom()).append("\n");
        sb.append("Famille: ").append(culture.getFamille()).append("\n");
        sb.append("Stade actuel: ").append(culture.getStadeCroissance()).append("\n");
        sb.append("Date plantation: ").append(culture.getDatePlantation()).append("\n");
        sb.append("Date recolte prevue: ").append(culture.getDateRecolte()).append("\n");
        sb.append("-------------------\n");
    }
    return sb.toString();
}
}