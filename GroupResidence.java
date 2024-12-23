import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class GroupResidence {
    /*
        Représente un groupe de résidence,
        stocke les résidences, les chambres, les personnes et les associations
        """

     */

    private Scanner scanner;
    private ArrayList<Chambre> allChambres;
    private ArrayList<Residence> allResidences;
    private ArrayList<Personne> allPersonnes;
    private Map<Personne,Chambre> associations;
    

    public GroupResidence(){
        this.allChambres = new ArrayList<>();
        this.allResidences = new ArrayList<>();
        this.allPersonnes = new ArrayList<>();
        this.associations = new LinkedHashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void loop(){
        /*
            méthode principale permettant de faire une boucle sur le 
            programme        
        */
        boolean conditionG = true;
        while (conditionG){
        
            boolean c = true;
            int action = 0;
            System.out.println("Choisissez une action à faire : \n(1) Afficher la répartition actuelle des chambres \n(2) Ajouter une personne \n(3) Ajouter une chambre \n(4) Arrêter le programme");
            // choix des actions et relancer l'entrée si elle ne correspond pas
            while (c) {
                if (scanner.hasNextInt()) {
                    action = scanner.nextInt();
                    scanner.nextLine();  
            
                    if (action > 0 && action < 5) {
                        c = false;
                    } else {
                        System.out.println("Veuillez entrer un nombre entre 1 et 3.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre valide.");
                    scanner.nextLine();  
                }
            }

            // identification de l'action
            if (action == 1){
                System.out.println(this);
            }else if (action == 2){
                this.addPersonneTerminal();
            }else if (action == 3){
                this.addChambreTerminal();
            }else if (action == 4){
                conditionG = false;
            }

            // actualiser les associations, personnes et chambres
            actualize();
        }
    
    }


    
    public void init(String pathChambres, String pathEtudiants){
        /*
                méthode de lancement au démarrage du programme
                */
        initChambres(pathChambres);
        initPersonnes(pathEtudiants);
        rankingChambres();
        rankingPersonnes();
        associationChambresPersonnes();

        // lancement de la boucle du programme
        loop();

    }

    public void actualize(){
        /*
                actualiser l'état du système
                */
        rankingChambres();
        rankingPersonnes();
        associationChambresPersonnes();

    }

    
    public void initChambres(String path){
        /*
                méthode qui extrait les données et les stockent
                */

        File csvFile = new File(path); // to read the CSV file
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            ReadCSVChambre obj_line;
            reader.readLine(); // Dropping first line
            while((line = reader.readLine()) != null) { // reader.readLine() -> to get the line and 
 
                obj_line = new ReadCSVChambre(line.split(";")); // spliting here but it was a choice
                
                Adress adress =  new Adress(obj_line.getCity(),obj_line.getCity_code(), obj_line.getAddress());
                Residence residence = null;

                // on trouve la résidence associée à l'adresse
                if (!(findResidenceByAdress(adress)==null)){
                    residence = findResidenceByAdress(adress);
                }else{
                    
                    residence = new Residence(adress);
                    allResidences.add(residence);
                } 

                Note scores = new Note(obj_line.getScores());


                Chambre chambre = new Chambre(obj_line.getId(), obj_line.getName(), obj_line.getSurface(), obj_line.getCreation_date(), obj_line.getLatest_renovation_date(), obj_line.getNb_locations(), scores);
                // ajout de la chambre dans le système complet et dans la résidence associée
                residence.addChambre(chambre);
                allChambres.add(chambre);
                
                
            }
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("An issue occured...");
        }
    }
    


    public Residence findResidenceByAdress(Adress adress){
        /*
                méthode qui permet de trouver la résidence associée à l'adresse
                si elle existe
                */
        Residence r = null;
        for (Residence residence : allResidences) {
            if (residence.getAdress().equals(adress)){
                r = residence;
            }
        }return r;
    }

    public void initPersonnes(String path){
        /*
                méthode qui extrait et stocke les données dans le système
                */
        File csvFile = new File(path); // to read the CSV file
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            
            String line;
            ReadCSVEtudiant obj_line;
            reader.readLine(); // Dropping first line
            while((line = reader.readLine()) != null) { // reader.readLine() -> to get the line and 
                // System.out.println(line);
                obj_line = new ReadCSVEtudiant(line.split(";")); // spliting here but it was a choice
                
                Personne personne;
                
                Note scores = new Note(obj_line.getNotes());
                if (obj_line.getContrat() != "null"){
                    Contrat contrat = new Contrat(obj_line.getContrat(), obj_line.getWorking_hours());
                    
                    if (obj_line.getINE() == "null"){
                        personne = new Personne(obj_line.getName(), obj_line.getSurname(), obj_line.getGender(), obj_line.getAge(), contrat);

                    }else{
                        personne = new Etudiant(obj_line.getId(), obj_line.getName(), obj_line.getSurname(), obj_line.getAge(), obj_line.getGender(), obj_line.getINE(), obj_line.getPromo(), scores,contrat);
                        
                    }
                }else{
                    personne= new Etudiant(obj_line.getId(), obj_line.getName(), obj_line.getSurname(), obj_line.getAge(), obj_line.getGender(), obj_line.getINE(), obj_line.getPromo(), scores);
                }

                // ajout de la personne au système
                allPersonnes.add(personne);

            }
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("An issue occured for Personne extract");
        }        

    }

    
    public void rankingChambres(){
        /*
                méthode qui classe les chambres 
                */
        // classement en fonction du résultat renvoyé par la méthode getAverage() des chambres
        getAllChambres().sort(Comparator.comparing(Chambre::getAverage).reversed());
    }

    public void rankingPersonnes(){
        /*
                méthode qui classe les personnes 
                */
        // classement en fonction du résultat renvoyé par la méthode getPoints() des personnes
        getAllPersonne().sort(Comparator.comparing(Personne::getPoints).reversed());
    }


    public void associationChambresPersonnes(){
        /*
                méthode qui associe les personnes aux chambres en fonction
                des classements
                */
        Chambre chambre = null;
        Personne personne;
        associations = new LinkedHashMap<>(); // on repart de zero pour rajouter dans l'ordre
        for (int index = 0; index < this.getAllPersonne().size(); index++){
            personne = getAllPersonne().get(index);
            
            if (index >= this.getAllChambres().size()){
                chambre = null;
            }else{
                chambre = getAllChambres().get(index);
            }
            associations.put(personne, chambre);

            // si il y a encore des chambres à distribuer
            if (index < getAllChambres().size()){
                Residence residence = getResidenceByChambre(chambre);
                residence.addPersonneAndChambre(personne, chambre);

            }
        }
    }


    
    
    
    public void deletePersonne(Personne personne){
        /*
                Suppression de personne dans le système
                */
        Chambre chambre = getAllAssociations().get(personne);
        Residence residence = getResidenceByChambre(chambre);
        
        residence.deletePersonne(personne);

        // suppression de l'association chambre personne
        getAllAssociations().remove(personne);
        getAllPersonne().remove(personne);
    }

    public void addPersonne(Personne personne){
        /*
                méthode qui ajoute des personnes dans le système
                */
        getAllPersonne().add(personne);
    }
    public void addChambre(Chambre chambre, Adress adress){
        /*
                méthode qui ajoute une chambre avec une adresse
                */
        Residence residence = findResidenceByAdress(adress);

        if (!(findResidenceByAdress(adress)==null)){
            residence = findResidenceByAdress(adress);
        }else{

            residence = new Residence(adress);
            allResidences.add(residence);
        }residence.addChambre(chambre);
        getAllChambres().add(chambre);
    }
    
    
    
    public void deleteChambre(Chambre chambre){
        /*
            méthode qui supprime une chambre dans le système
         */
        Personne personne = null;
        for (Map.Entry<Personne, Chambre> entry : associations.entrySet()) {
            if (entry.getValue().equals(chambre)) {
                personne = entry.getKey();  // Retourne la personne associée
            }
        }
        
        Residence residence = getResidenceByChambre(chambre);
        residence.deleteChambre(chambre);
        getAllAssociations().remove(personne);
        getAllChambres().remove(chambre);
    }
    
    
    public String availabilityChambres(){
        /*
            méthode qui renvoie les chambres disponibles
         */
        String r = "";
        for (Residence residence : getAllResidences()) {
            r += residence.toString();
        }
        return r;
    }
    
    public String displayAssociationsAll(){
        /*
            méthode qui renvoie les associations des chambres et des personnes
         */
        String r = "";
        for (Residence residence : getAllResidences()) {
            r += residence.toString();
        }return r + "\n" + getPersonnesWithOut();
    }
    public String toString(){
        /*
            méthode qui permet d'afficher l'état du système
         */
        String r = "";
        for (Map.Entry<Personne, Chambre> entry : associations.entrySet()) {
            Chambre c = entry.getValue();
            Personne p = entry.getKey();
            r += p.getName() + " ".repeat(10-p.getName().length()) + p.getSurname() + " ".repeat(10-p.getSurname().length()) + ": " + p.getPoints() + " ".repeat(10 - Float.toString(p.getPoints()).length()) + p.getContrat() + " ".repeat(30 - p.getContrat().toString().length()) + "->";
            if (c != null){
                r += " " + c.getName() + " " + c.getId() + " : " + c.getAverage() + "\n";  
            }else{
                r += " " + "Pas de chambre \n";
            }
        }

        return r;
        
    }
    public String getPersonnesWithOut(){
        /*
            méthode qui renvoie les personnes sans chambres
         */
        String r = "Personnes sans logement : \n";
        
        for (Map.Entry<Personne, Chambre> entry : getAllAssociations().entrySet()) {
            if (entry.getValue() == null){
                r += entry.getKey().getName() + " " + entry.getKey().getSurname()  + " " + entry.getKey().getPoints() + "\n";
            }
        }

        return r;
    }
    
    /* Getters */
    
    public Residence getResidenceByChambre(Chambre chambre) {
        /*
            méthode qui renvoie la résidence en fonction de la chambre
         */
        Residence r = null;
        for (Residence residence : getAllResidences()) {
            if (residence.getAssociations().containsKey(chambre)){
                r = residence;
                break;
            }
        }
        return r;
    }
    
    public ArrayList<Chambre> getAllChambres(){
        /*
            renvoie les chambres
         */
        return allChambres;
    }
    
    public ArrayList<Residence> getAllResidences(){
        /*
            renvoie les résidences
         */
        return allResidences;
    }
    
    public ArrayList<Personne> getAllPersonne(){
        /*
            renvoie les personnes
         */
        return allPersonnes;
    }
    public Map<Personne, Chambre> getAllAssociations(){
        /*
            renvoie les associations
         */
    
        return associations;
    }

    private int[] parse_list_int(String notes) {
        /*
            extrait d'une chaine de caractère les nombrers dans la liste
         */
        // function to convert the string of notes to a list of int
        String[] n = notes.replace("[", "").replace("]", "").split(",");
        int[] res = new int[n.length];
        for (int i = 0; i<n.length; i++){
            // System.out.println(n[i]);
            res[i] = Integer.parseInt(n[i]);
        }
        return res;
    }

    public void addPersonneTerminal() {
        /*
            méthode qui ajoute une personne par terminal
         */
        try {
            System.out.println("Ajout d'une personne :");

            System.out.print("Nom : ");
            String name = scanner.nextLine();

            System.out.print("Nom de famille: ");
            String surname = scanner.nextLine();

            System.out.print("Genre : ");
            String gender = scanner.nextLine();

            int age = -1;
            while (age <= 0) {
                System.out.print("Age : ");
                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    scanner.nextLine();
                    if (age <= 0) {
                        System.out.println("L'âge doit être un nombre positif. Essayez à nouveau.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre valide pour l'âge.");
                    scanner.nextLine();
                }
            }

            System.out.print("La personne travaille-t-elle ? O/N ");
            String rep = scanner.nextLine();
            Contrat contrat = null;

            while (!rep.equalsIgnoreCase("O") && !rep.equalsIgnoreCase("N")) {
                System.out.println("Réponse invalide. Entrez O pour Oui ou N pour Non.");
                System.out.print("La personne travaille-t-elle ? O/N ");
                rep = scanner.nextLine();
            }

            if (rep.equalsIgnoreCase("O")) {
                System.out.print("Id du contrat : ");
                String contratstr = scanner.nextLine();

                while (true) {
                    System.out.print("Heures de travail \"18-19,19-0\" : ");
                    String workinghours = scanner.nextLine();
                    try {
                        contrat = new Contrat(contratstr, workinghours);
                        contrat.getTotalHours();
                        break;
                    } catch (Exception e) {
                        System.out.println("Erreur pour les heures de travail. Veuillez réessayer.");
                    }
                }

            }

            System.out.print("La personne est étudiante ? O/N ");
            rep = scanner.nextLine();

            while (!rep.equalsIgnoreCase("O") && !rep.equalsIgnoreCase("N")) {
                System.out.println("Réponse invalide. Entrez O pour Oui ou N pour Non.");
                System.out.print("La personne est étudiante ? O/N ");
                rep = scanner.nextLine();
            }

            Personne personne = null;

            if (rep.equalsIgnoreCase("N")) {
                if (contrat != null) {
                    personne = new Personne(name, surname, gender, age, contrat);
                } else {
                    personne = new Personne(name, surname, gender, age);
                }
                System.out.println("Personne créée.");
            } else if (rep.equalsIgnoreCase("O")) {
                System.out.print("Entrez l'identifiant de l'étudiant : ");
                String id = scanner.nextLine();

                System.out.print("Entrez le numéro INE de l'étudiant : ");
                String INE = scanner.nextLine();

                int promo = -1;
                while (promo <= 0) {
                    System.out.print("Entrez l'année de promotion de l'étudiant : ");
                    if (scanner.hasNextInt()) {
                        promo = scanner.nextInt();
                        scanner.nextLine();
                        if (promo <= 0) {
                            System.out.println("L'année de promotion doit être un nombre positif.");
                        }
                    } else {
                        System.out.println("Veuillez entrer un nombre valide pour l'année de promotion.");
                        scanner.nextLine();
                    }
                }
                Note notes = null;
                while (true){
                    try{
                        System.out.print("Entrez vos notes '[15,18]': ");
                        String notesRaw = scanner.nextLine();
                        notes = new Note(parse_list_int(notesRaw));
                        notes.getAverage();
                        break;
                    }catch (Exception e){
                        System.out.println("Erreur pour les notes. Veuillez réessayer, voici un exemple : '[15,18]'");
                    }
                }


                if (contrat == null) {
                    personne = new Etudiant(id, name, surname, age, gender, INE, promo, notes);
                } else {
                    personne = new Etudiant(id, name, surname, age, gender, INE, promo, notes, contrat);
                }
                System.out.println("Etudiant créé.");
            }

            if (personne != null) {
                this.allPersonnes.add(personne);
                System.out.println(personne.getName() + " " + personne.getSurname() + " a été ajouté.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'ajout de la personne: " + e.getMessage());
        }
    }

    public void addChambreTerminal(){
        /*
            méthode qui ajoute une chambre par terminal
         */
        try{
            System.out.print("Entrez l'ID de la chambre: ");
            String id = scanner.nextLine();

            System.out.print("Entrez le nom de la chambre: ");
            String name = scanner.nextLine();
            float surface = -1;
            while (surface <= 0) {
                System.out.print("Entrez la surface de la chambre (en m²): ");
                if (scanner.hasNextFloat()) {
                    surface = scanner.nextFloat();
                    scanner.nextLine();
                    if (surface <= 0) {
                        System.out.println("La surface doit être un nombre positif. Essayez encore.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre valide pour la surface.");
                    scanner.nextLine();
                }
            }

            int creationDate = -1;
            while (creationDate <= 0) {
                System.out.print("Entrez la date de création de la chambre (année): ");
                if (scanner.hasNextInt()) {
                    creationDate = scanner.nextInt();
                    scanner.nextLine();
                    if (creationDate <= 0) {
                        System.out.println("La date de création doit être un nombre positif.");
                    }
                } else {
                    System.out.println("Veuillez entrer une année valide.");
                    scanner.nextLine();
                }
            }

            int renovationDate = -1;
            while (renovationDate < creationDate) {
                System.out.print("Entrez la date de dernière rénovation (année): ");
                if (scanner.hasNextInt()) {
                    renovationDate = scanner.nextInt();
                    scanner.nextLine();
                    if (renovationDate < creationDate) {
                        System.out.println("La date de rénovation doit être égale ou après la date de création.");
                    }
                } else {
                    System.out.println("Veuillez entrer une année valide pour la date de rénovation.");
                    scanner.nextLine();
                }
            }

            int nbLocations = -1;
            while (nbLocations <= 0) {
                System.out.print("Entrez le nombre de locations passées : ");
                if (scanner.hasNextInt()) {
                    nbLocations = scanner.nextInt();
                    scanner.nextLine();
                    if (nbLocations <= 0) {
                        System.out.println("Le nombre de locations doit être un nombre positif.");
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre valide pour le nombre de locations.");
                    scanner.nextLine();
                }
            }

            Scanner scanner = new Scanner(System.in);

            System.out.print("Entrez le nom de la ville: ");
            String city = scanner.nextLine();

            int cityCode = -1;
            while (cityCode <= 0) {
                System.out.print("Entrez le code postal de la ville: ");
                if (scanner.hasNextInt()) {
                    cityCode = scanner.nextInt();
                    scanner.nextLine();
                    if (cityCode <= 0) {
                        System.out.println("Le code postal doit être un nombre positif.");
                    }
                } else {
                    System.out.println("Veuillez entrer un code postal valide.");
                    scanner.nextLine();
                }
            }
            Note notes = null;
            while (true){
                System.out.print("Entrez vos notes '[4,3]' : ");
                String notesRaw = scanner.nextLine();
                try{
                    notes = new Note(parse_list_int(notesRaw));
                    notes.getAverage();
                    break;
                } catch (Exception e) {
                    System.out.println("Erreur pour les notes. Veuillez réessayer, voici un exemple '[4,3]' : ");
                }

            }

            System.out.print("Entrez le nom de la rue: ");
            String street = scanner.nextLine();

            Adress adress = new Adress(city, cityCode, street);

            Residence residence = findResidenceByAdress(adress);


            Chambre chambre = new Chambre(id, name, surface, creationDate, renovationDate, nbLocations, notes);
            if (!(findResidenceByAdress(adress)==null)){
                residence = findResidenceByAdress(adress);
            }else{

                residence = new Residence(adress);
                allResidences.add(residence);
            }residence.addChambre(chambre);
            allChambres.add(chambre);

            System.out.println("Adresse créée avec succès : " + adress);
        }catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'ajout de la chambre " + e.getMessage());
        }


    }

    public void addResidence(Residence residence){
        /*
            méthode qui ajoute une résidence
         */
        allResidences.add(residence);
    }

    public void deleteResidence(Residence residence){
        /*
            méthode qui supprime résidence
         */
        allResidences.remove(residence);
    }
    public static void main(String[] args) {
        GroupResidence system = new GroupResidence();
        if (args.length == 2){
            system.init(args[0], args[1]);
        }else{
            system.init("Ressources/liste_chambres.csv", "Ressources/liste_etudiants.csv");
        }
    }
}