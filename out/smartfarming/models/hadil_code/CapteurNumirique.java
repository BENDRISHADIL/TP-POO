package out.smartfarming.models.hadil_code;

public abstract class CapteurNumirique extends Capteur {
    private int valeur;
    private String uniteMesure;//ici le type de unit peut etre mm/C/... dependes on the type of the capteure
   
   
   
   
   
   
   
   
   
    public CapteurNumirique() {
        codeCapteur++;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    
}
