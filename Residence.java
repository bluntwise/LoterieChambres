import java.util.ArrayList;

public class Residence {

    private Adress adress;
    private ArrayList<Chambre> chambres;

    public Residence(Adress adress){
        this.adress = adress;
        this.chambres = new ArrayList<>();
    }

    public String toString(){
        return adress.toString() + " " + chambres.size();
    }

    public Adress getAdress(){
        return this.adress;
    }

    public void addChambre(Chambre chambre){
        chambres.add(chambre);
    }

    public String displayChambres(){
        String r = "";
        for (Chambre chambre : chambres) {
            r += chambre.toString();
        }return r;
    }
    public ArrayList<Chambre> getChambres(){
        return this.chambres;
    }
}