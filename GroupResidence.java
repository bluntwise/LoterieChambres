import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class GroupResidence {

    public static Personne personne;
    private LinkedHashMap<Chambre,Boolean> allChambres;
    private ArrayList<Residence> allResidences;
    private ArrayList<Personne> allPersonnes;
    private Map<Personne,Chambre> associations;
    

    public GroupResidence(){
        
        this.allChambres = new LinkedHashMap<>();
        this.allResidences = new ArrayList<>();
        this.allPersonnes = new ArrayList<>();
        this.associations = new LinkedHashMap<>();
    }

    public void initGeneral(){
        initChambres("Ressources/liste_chambres.csv");
        initPersonnes("Ressources/liste_etudiants.csv");
        rankingChambres();
        rankingPersonnes();
        associationChambresPersonnes();

    }

    public void initChambres(String path){
        File csvFile = new File(path); // to read the CSV file
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            ReadCSVChambre obj_line;
            reader.readLine(); // Dropping first line
            int i = 0;
            while((line = reader.readLine()) != null) { // reader.readLine() -> to get the line and 
 
                obj_line = new ReadCSVChambre(line.split(";")); // spliting here but it was a choice
                
                Adress adress =  new Adress(obj_line.getCity(),obj_line.getCity_code(), obj_line.getAddress());
                Residence residence = null;
                
                if (!(findResidenceByAdress(adress)==null)){
                    residence = findResidenceByAdress(adress);
                }else{
                    residence = new Residence(adress);
                    allResidences.add(residence);
                }

                Note scores = new Note(obj_line.getScores());

                Chambre chambre = new Chambre(obj_line.getId(), obj_line.getName(),residence , obj_line.getSurface(), obj_line.getCreation_date(), obj_line.getLatest_renovation_date(), obj_line.getNb_locations(), scores);
                residence.addChambre(chambre);
                
                i++;
            }
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("An issue occured...");
        }
    }
    


    public Residence findResidenceByAdress(Adress adress){
        Residence r = null;
        for (Residence residence : allResidences) {
            if (residence.getAdress().equals(adress)){
                r = residence;
            }
        }

        return r;
    }

    public void initPersonnes(String path){

        File csvFile = new File(path); // to read the CSV file
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            
            String line;
            ReadCSVEtudiant obj_line;
            reader.readLine(); // Dropping first line
            while((line = reader.readLine()) != null) { // reader.readLine() -> to get the line and 
                // System.out.println(line);
                obj_line = new ReadCSVEtudiant(line.split(";")); // spliting here but it was a choice

                Personne Personne;

                Note scores = new Note(obj_line.getNotes());
                if (obj_line.getContrat() != "null"){
                    Contrat contrat = new Contrat(obj_line.getContrat(), obj_line.getWorking_hours());
                    
                    if (obj_line.getINE() == "null"){
                        Personne = new Personne(obj_line.getName(), obj_line.getSurname(), obj_line.getGender(), obj_line.getAge(), contrat);

                    }else{
                        Personne = new Etudiant(obj_line.getId(), obj_line.getName(), obj_line.getSurname(), obj_line.getAge(), obj_line.getGender(), obj_line.getINE(), obj_line.getPromo(), scores,contrat);
                        
                    }
                }else{
                    Personne = new Etudiant(obj_line.getId(), obj_line.getName(), obj_line.getSurname(), obj_line.getAge(), obj_line.getGender(), obj_line.getINE(), obj_line.getPromo(), scores);
                }
                personne = Personne;
                // System.out.println(Personne.getAverage() + " | " + test);
                allPersonnes.add(Personne);

            }
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("An issue occured for Personne extract");
        }
    }

    
    public void rankingChambres(){
    
        for (Residence residence: getAllResidences()) {
            allChambres.putAll(residence.getChambres());
        }
        allChambres = allChambres.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey(Comparator.comparing(Chambre::getAverage).reversed()))
            .collect(Collectors.toMap(
                    Map.Entry::getKey,  
                    Map.Entry::getValue,  
                    (e1, e2) -> e1,  
                    LinkedHashMap::new  
            ));
        
    }

    public void rankingPersonnes(){
        getAllPersonne().sort(Comparator.comparing(Personne::getPoints).reversed());
    }

    public void associationChambresPersonnes(){
        Chambre chambre = null;
        Personne Personne;
        
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

            Personne = this.getAllPersonne().get(index);
            allChambres.put(chambre, true);
            associations.put(Personne, chambre);

            if (index < this.getAllChambres().size() && chambre != null){
                addAssociationToResidence(Personne, chambre);
            }
            
        }
    }

    public void addAssociationToResidence(Personne Personne, Chambre chambre){
        Residence residence = getResidenceByChambre(chambre);
        residence.addPersonne(Personne);
    }
    

    public String displayAssociationsAll(){
        String r = "";

        for (Map.Entry<Personne, Chambre> entry : associations.entrySet()) {
            Chambre c = entry.getValue();
            Personne p = entry.getKey();
            r += p.getName() + " " + p.getSurname() + " : " + p.getPoints() + " " + p.getContrat() + "->";
            if (c != null){
                r += c.getName() + " " + c.getId() + " : " + c.getAverage() + "\n";  
            }else{
                r += "Pas de chambre \n";
            }
        }

        return r;
    }

    public void deletePersonne(Personne personne){

        Chambre chambre = getAllAssociations().get(personne);
        Residence residence = getResidenceByChambre(chambre);

        residence.deletePersonne(personne,chambre);

        getAllAssociations().remove(personne);
        getAllChambres().put(chambre, false);
        getAllPersonne().remove(personne);

    }



    public void deleteChambre(Chambre chambre){
        Personne personne = null;
        for (Map.Entry<Personne, Chambre> entry : associations.entrySet()) {
            if (entry.getValue().equals(chambre)) {
                personne = entry.getKey();  // Retourne la personne associée
            }
        }

        Residence residence = getResidenceByChambre(chambre);
        residence.deleteChambre(personne, chambre);
        getAllAssociations().remove(personne);
        getAllChambres().remove(chambre);
    }


    public String availabilityChambres(){
        String r = "";
        for (Residence residence : getAllResidences()) {
            r += residence.displayChambres();
        }
        return r;
    }

    /* Getters */

    public Residence getResidenceByChambre(Chambre chambre){

        Residence r = null;
        for (Residence residence : getAllResidences()) {
            if (residence.getAllChambres().containsKey(chambre)){
                r = residence;
                break;
            }
        }
        return r;
    }

    public Map<Chambre,Boolean> getAllChambres(){
        return allChambres;
    }

    public ArrayList<Residence> getAllResidences(){
        return allResidences;
    }

    public ArrayList<Personne> getAllPersonne(){
        return allPersonnes;
    }

    public Map<Personne, Chambre> getAllAssociations(){
        return associations;
    }

    public String toString(){
        String r = "";
        for (Residence residence : getAllResidences()) {
            r += residence.toString();
        }return r;
    }

   

    public static void main(String[] args) {
        GroupResidence system = new GroupResidence();
        system.initGeneral();
        System.out.println(system.getAllResidences().get(0).getAllChambres().size());





    }
}