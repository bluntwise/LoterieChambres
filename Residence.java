import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Residence extends GroupResidence{

    private Adress adress;

    public Residence(Adress adress){
        super();
        this.adress = adress;
    }



    public String toString(){
        String r = "\n\tResidence : " + getAdress() + "\n\n";
        this.associationChambresPersonResidence();
        
        for (Map.Entry<Personne, Chambre> entry : super.getAllAssociations().entrySet()) {
            r += entry.getKey().getName() + " " + entry.getKey().getSurname() + " " + entry.getKey().getPoints() + " -> " + entry.getValue().getName() + " " + entry.getValue().getAverage() + " \n";
        }
        return r ;
        // return adress.toString() + " : chambres -> " + super.getAllChambres().size() + " | personnes : " + super.getAllPerson().size() + "\n";
    }

    public void associationChambresPersonResidence(){
        Chambre chambre = null;
        Personne person;

        rankingChambres();
        rankingPersonnes();
        
        Iterator<Chambre> chambIterator = this.getAllChambres().keySet().iterator();
        int index = 0;

        for (index = 0; index < this.getAllPersonne().size(); index++){
            if (chambIterator == null){
                chambre = null;
            }else{
                try{
                    chambre = chambIterator.next();
                }catch(Exception exception){
                    chambre = null;
                }
            }

            person = this.getAllPersonne().get(index);
            super.getAllChambres().put(chambre, true);
            getAllAssociations().put(person, chambre);

        }
    }

    public void addPersonne(Personne personne){
        getAllPersonne().add(personne);
    }

    public void deletePersonne(Personne personne,Chambre chambre){
        getAllAssociations().remove(personne);
        getAllChambres().put(chambre, false);
        getAllPersonne().remove(personne);
    }
    
    public void addChambre(Chambre chambre){
        getAllChambres().put(chambre,false);
    }
    
    public String displayChambres(){
        String r = "";
        Chambre chambre;
        Boolean status;
        for (Map.Entry<Chambre, Boolean> entry : getAllChambres().entrySet()) {
            chambre = entry.getKey();
            status = entry.getValue();
            r += chambre.toString() + " | status : " + status;
        }return r;
    }


    /*Getters */

    public Map<Chambre,Boolean> getChambres(){
        return this.getAllChambres();
    }
    
    public Adress getAdress(){
        return this.adress;
    }
}