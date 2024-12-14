import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Systeme {

    
    private ArrayList<Chambre> allChambres;
    private ArrayList<Residence> allResidences;
    private ArrayList<Person> allPersons;

    public Systeme(){
        this.allChambres = new ArrayList<>();
        this.allResidences = new ArrayList<>();
        this.allPersons = new ArrayList<>();
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
                if (obj_line.getContrat() != null){
                    Contrat contrat = new Contrat(obj_line.getContrat(), obj_line.getWorking_hours());
                    if (obj_line.getINE() == null){
                        person = new Person(obj_line.getName(), obj_line.getSurname(), obj_line.getGender(), obj_line.getAge(), contrat);
                    }else{
                        person = new Etudiant(obj_line.getId(), obj_line.getName(), obj_line.getSurname(), obj_line.getAge(), obj_line.getGender(), obj_line.getINE(), obj_line.getPromo(), scores,contrat);
                    }
                }else{
                    person = new Etudiant(obj_line.getId(), obj_line.getName(), obj_line.getSurname(), obj_line.getAge(), obj_line.getGender(), obj_line.getINE(), obj_line.getPromo(), scores);
                }
                allPersons.add(person);

            }
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("An issue occured for Person extract");
        }
    }

    
    public void rankingChambres(){
        ArrayList<Chambre> l = getAllChambres();
        l.sort(Comparator.comparing(Chambre::getAverage));
        for (Chambre chambre : l) {
            System.out.println(chambre);
        }
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
        system.rankingChambres();
    }
}