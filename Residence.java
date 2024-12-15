import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Residence {

    private Adress adress;
    private TreeMap<Chambre,Boolean> chambres;
    private ArrayList<Person> personnes;
    private Map<Person,Chambre> associations;

    public Residence(Adress adress){
        this.adress = adress;
        Comparator<Chambre> comparator = Comparator.comparing(Chambre::getAverage);
        chambres = new TreeMap<>(comparator);
        this.personnes = new ArrayList<>();
        this.associations = new LinkedHashMap<>();
    }



    public String toString(){
        return adress.toString() + " " + chambres.size();
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

    public Adress getAdress(){
        return this.adress;
    }
}