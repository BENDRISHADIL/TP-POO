package smartfarming.models.hadil_code;

public class PlageSeuils {
    private double valeurMin;
    private double valeurMax;

    public PlageSeuils(double valeurMin, double valeurMax) {
        this.valeurMin = valeurMin;
        this.valeurMax = valeurMax;
    }
    //-------------
    //getters
    //-----------------
    public double getValeurMin() {
        return valeurMin;
    }
    public double getValeurMax() {
        return valeurMax;
    }

    /*vérifier si une valeurs est hors seuil autoriser */
    public boolean estHorsSeuil (double valeur) {
        if (valeur<valeurMin ||valeur>valeurMax){
            return true ;
        }else {
            return false ;
        }
    }


    /*Seters and getters */
    public double getvaleurMin(){
        return valeurMin;
    }
    public double getvaleurMax(){
        return valeurMax;
    }

    @Override public String toString(){
        return "seuils[min = "+valeurMin+" , max = "+valeurMax+"]";
    }
}
