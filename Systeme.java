import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Systeme {

    
    private ArrayList<Chambre> allChambres;
    private ArrayList<Residence> allResidences;
    private ArrayList<Person> allPersons;
    private Map<Chambre, Person> associations;
    

    public Systeme(){
        this.allChambres = new ArrayList<>();
        this.allResidences = new ArrayList<>();
        this.allPersons = new ArrayList<>();
        this.associations = new LinkedHashMap<>();
    }


    public void initChambres(String path){
        File csvFile = new File(path); // to read the CSV file
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            ReadCSVChambre obj_line;
            reader.readLine(); // Dropping first line
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
                allChambres.add(chambre);
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

    public void initPersons(String path){

        File csvFile = new File(path); // to read the CSV file
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            ReadCSVEtudiant obj_line;
            reader.readLine(); // Dropping first line
            while((line = reader.readLine()) != null) { // reader.readLine() -> to get the line and 
                // System.out.println(line);
                obj_line = new ReadCSVEtudiant(line.split(";")); // spliting here but it was a choice

                Person person;

                Note scores = new Note(obj_line.getNotes());
                if (obj_line.getContrat() != "null"){
                    Contrat contrat = new Contrat(obj_line.getContrat(), obj_line.getWorking_hours());
                    
                    if (obj_line.getINE() == "null"){
                        person = new Person(obj_line.getName(), obj_line.getSurname(), obj_line.getGender(), obj_line.getAge(), contrat);

                    }else{
                        person = new Etudiant(obj_line.getId(), obj_line.getName(), obj_line.getSurname(), obj_line.getAge(), obj_line.getGender(), obj_line.getINE(), obj_line.getPromo(), scores,contrat);
                        
                    }
                }else{
                    person = new Etudiant(obj_line.getId(), obj_line.getName(), obj_line.getSurname(), obj_line.getAge(), obj_line.getGender(), obj_line.getINE(), obj_line.getPromo(), scores);
                }
                // System.out.println(person.getAverage() + " | " + test);
                allPersons.add(person);

            }
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("An issue occured for Person extract");
        }
    }

    
    public void rankingChambres(){
        
        getAllChambres().sort(Comparator.comparing(Chambre::getAverage));
        // for (Chambre chambre : getAllChambres()) {
        //     System.out.println(chambre);
        // }
    }

    public void rankingPersons(){
        getAllPerson().sort(Comparator.comparing(Person::getPoints));
        // for (Person person : getAllPerson()) {
        //     System.out.println(person);
        // }
    }

    public void associationChambresPersons(){
        Chambre chambre;
        Person person;
        for (int i = 0; i < this.getAllChambres().size(); i++) {
            chambre = this.getAllChambres().get(i);
            person = this.getAllPerson().get(i);
            associations.put(chambre,person);
        }
    }

    public String displayAssociations(){
        String r = "";

        for (Map.Entry<Chambre, Person> entry : associations.entrySet()) {
            Chambre c = entry.getKey();
            Person p = entry.getValue();
            r += c.getName() + " " + c.getId() + " : " + c.getAverage() + " -> " + p.getName() + " : " + p.getPoints() + " " + p.getContrat() + "\n" + p.toString();
            if (p instanceof Etudiant){
            }
            
        }

        return r;
    }

    /* Getters */

    public ArrayList<Chambre> getAllChambres(){
        return allChambres;
    }

    public ArrayList<Residence> getAllResidences(){
        return allResidences;
    }

    public ArrayList<Person> getAllPerson(){
        return allPersons;
    }

    public static void main(String[] args) {
        Systeme system = new Systeme();
        system.initChambres("./Ressources/liste_chambres.csv");
        system.initPersons("./Ressources/liste_etudiants.csv");
        system.rankingPersons();
        system.rankingChambres();
        system.associationChambresPersons();
        // system.displayAssociations();
        System.out.println(system.displayAssociations());
        // for (Chambre chambre : system.getAllChambres()) {
        //     System.out.println(chambre.getAverage());
        // }
    }
}