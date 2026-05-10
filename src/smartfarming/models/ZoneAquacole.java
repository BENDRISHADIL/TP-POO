package smartfarming.models;

import java.util.List;
import java.util.ArrayList;

public class ZoneAquacole extends Zone {

    private List<Bassin> bassins;

 public ZoneAquacole(String code, String nom) {
    super(code, nom);
    this.bassins = new ArrayList<>();
}
    // Add a bassin to this zone
    public void ajouterBassin(Bassin bassin) {
        this.bassins.add(bassin);
    }

    // Getter
    public List<Bassin> getBassins() { return bassins; }

    // Count entities → number of bassins
    @Override
    public int getNombreEntites() {
        return bassins.size();
    }
}