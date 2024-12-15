import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Residence {

    private Adress adress;
    private Map<Chambre,Boolean> chambres;
    private ArrayList<Person> personnes;
    private Map<Person,Chambre> associations;

    public Residence(Adress adress){
        this.adress = adress;
        this.chambres = new LinkedHashMap<>();
        this.personnes = new ArrayList<>();
        this.associations = new LinkedHashMap<>();
    }



    public String toString(){
        return adress.toString() + " " + chambres.size();
    }

    public Adress getAdress(){
        return this.adress;
    }

    public void addChambre(Chambre chambre){
        chambres.put(chambre,false);
    }

    public String displayChambres(){
        String r = "";
        Chambre chambre;
        Boolean status;
        for (Map.Entry<Chambre, Boolean> entry : chambres.entrySet()) {
            chambre = entry.getKey();
            status = entry.getValue();
            r += chambre.toString() + " | status : " + status;
        }return r;
    }
    public Map<Chambre,Boolean> getChambres(){
        return this.chambres;
    }
}