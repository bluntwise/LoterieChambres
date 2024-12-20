class MainTest {
    public static void main(String[] args) {
        AdressTest.runTests();
        System.out.println("---------------class Adress Test Valids---------------");
        NoteTest.runTests();
        System.out.println("---------------class Note Test Valids---------------");
        ContratTest.runTests();
        System.out.println("---------------class Contrat Test Valids---------------");
        ChambreTest.runTests();
        System.out.println("---------------class Chambre Test Valids---------------");
        EtudiantTest.runTests();
        System.out.println("---------------class Etudiant Test Valids---------------");
        PersonneTest.runTests();
        System.out.println("---------------class Personne Test Valids---------------");
        ResidenceTest.runTests();
        System.out.println("---------------class Residence Test Valids---------------");
    }
}

class AdressTest {
    public static void runTests() {
        Adress adress1 = new Adress("Lannion", 22300, "Rue de l'école");
        Adress adress2 = new Adress("Lannion", 22300, "Rue Enssat");
        assert !adress1.equals(adress2);
        assert adress1.equals(adress1);
        assert adress1.getCity().equals("Lannion");
        assert adress2.getCity().equals("Lannion");
        assert adress1.getCityCode() == 22300;
        assert adress2.getCityCode() == 22300;
        assert adress1.toString().equals("Rue de l'école Lannion 22300");
    }
}

class NoteTest {
    public static void runTests() {
        int[] notesRaw = {15, 16, 17};
        Note notes = new Note(notesRaw);
        assert notes.toString().equals("[15, 16, 17]");
        assert notes.getAverage() == 16;
        assert java.util.Arrays.equals(notes.getNotes(), notesRaw);
    }
}

class ContratTest {
    public static void runTests() {
        Contrat contrat = new Contrat();
        assert contrat.getContrat().equals("Pas de contrat");
        Contrat contrat2 = new Contrat("4654654654", "9-17");
        assert contrat2.getContrat().equals("4654654654");
        assert java.util.Arrays.equals(contrat2.getWorkingHours(), new String[]{"9-17"});
        Contrat contrat3 = new Contrat("1293U91", "8-10,17-21");
        assert contrat3.getTotalHours() == 6;
        assert contrat3.toString().equals("1293U91 [8-10,17-21]");
    }
}

class ChambreTest {
    public static void runTests() {
        Note note = new Note(new int[]{4, 3});
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, note);
        assert chambre.toString().equals("qsjmklgh4s A101 25.5 2015 2020 5 [4, 3] 3.5");
        assert chambre.getAverage() == 3.5;
        assert chambre.getId().equals("qsjmklgh4s");
        assert chambre.getSurface() == 25.5f;
        assert chambre.getCreation_date() == 2015;
        assert chambre.getLatest_renovation_date() == 2020;
        assert chambre.getNb_locations() == 5;
        assert chambre.getScores().equals(note);
    }
}

class EtudiantTest {
    public static void runTests() {
        Note notes1 = new Note(new int[]{15, 16, 17});
        Note notes2 = new Note(new int[]{2, 3, 4});
        Contrat contrat = new Contrat("1234", new String[]{"9-17"});
        Etudiant etudiant1 = new Etudiant("E123", "Alan", "DELY", 22, "M", "INE12345", 2025, notes1);
        Etudiant etudiant2 = new Etudiant("E124", "Lisa", "ROGER", 21, "F", "INE12346", 2024, notes2, contrat);

        assert etudiant1.getId().equals("E123");
        assert etudiant1.getINE().equals("INE12345");
        assert etudiant1.getPromo() == 2025;
        assert etudiant1.getNotes().equals(notes1);
        assert etudiant1.toString().equals("Alan DELY M 22 Pas de contrat [] E123 INE12345 2025 [15, 16, 17] 16.0");
        assert etudiant1.getAverage() == 16.0f;
        assert etudiant1.getPoints() == 2027 - 2025 + 16;
        assert etudiant2.getPoints() == 2027 - 2024 + 3 + contrat.getTotalHours();
    }
}

class PersonneTest {
    public static void runTests() {
        Contrat contrat = new Contrat("1234", new String[]{"9-17"});
        Personne personne1 = new Personne("Ilan", "Chasse", "M", 22);
        Personne personne2 = new Personne("Ilan", "Chasse", "M", 22, contrat);

        assert personne1.getName().equals("Ilan");
        assert personne1.getSurname().equals("Chasse");
        assert personne1.getGender().equals("M");
        assert personne1.getAge() == 22;
        assert personne1.getContrat().getContrat().equals("Pas de contrat") : "PAs de ";
        assert personne2.getContrat().getContrat().equals("1234") : "L'id du contrat renvoyé n'est pas le bon.";
        assert personne1.toString().equals("Ilan Chasse M 22 Pas de contrat []") : "La méthode toString ne renvoie pas le bon contenu.";
        assert personne1.getAverage() == 0.0f : "La moyenne ne correspond pas à 0.";
        assert personne1.getPoints() == 0f : "Les points ne correspondent pas. ";
        assert personne2.getPoints() == contrat.getTotalHours() : "Les points ne correspondent au nombre d'heures.";
    }
}

class ResidenceTest {
    public static void runTests() {
        Adress adress = new Adress("Rennes", 35000, "Rue Exemple");
        Residence residence = new Residence(adress);
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, new Note(new int[]{4, 3}));
        Personne personne = new Personne("Ilan", "Chasse", "M", 22);

        residence.addPersonneAndChambre(personne, chambre);
        assert residence.getAssociations().get(chambre).equals(personne) : "Les associations ne comportent pas " + personne.toString();
        residence.deletePersonne(personne);
        assert !residence.getAssociations().containsKey(chambre) : "Les associations comportent encore " + personne.toString();
        residence.addChambre(chambre);
        assert residence.getAssociations().containsKey(chambre) : "Les associations ne comportent pas " + chambre.toString();
        residence.deleteChambre(chambre);
        assert !residence.getAssociations().containsKey(chambre) : "Les associations comportent encore " + chambre.toString();
        assert residence.getAdress().equals(adress) : "La residence ne renvoie la bonne adresse.";
    }
}

class GroupResidenceTest {
    public static void runTests() {
        Adress adress1 = new Adress("Rennes", 35000, "Rue Exemple");
        Residence residence1 = new Residence(adress1);
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, new Note(new int[]{4, 3}));
        Personne personne = new Personne("Ilan", "Chasse", "M", 22);

        residence1.addPersonneAndChambre(personne, chambre);

        Adress adress2 = new Adress("Lannion", 22300, "Rue Enssat");
        Residence residence2 = new Residence(adress2);
        Chambre chambre1 = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, new Note(new int[]{4, 3}));
        Personne personne1 = new Personne("Ilan", "Chasse", "M", 22);

        residence2.addPersonneAndChambre(personne1, chambre1);

        GroupResidence groupResidence = new GroupResidence();
        groupResidence.addResidence(residence1);
        groupResidence.addResidence(residence2);

        assert groupResidence.getAllResidences().size() == 2 :
                "Le groupe devrait contenir 2 résidences après ajout";

        assert groupResidence.getAllResidences().contains(residence1) :
                "La résidence de Rennes devrait être présente";

        assert groupResidence.getAllResidences().contains(residence2) :
                "La résidence de Lannion devrait être présente";

        groupResidence.deleteResidence(residence1);
        assert groupResidence.getAllResidences().size() == 1 :
                "Le groupe devrait contenir 1 résidence après suppression";

        assert !groupResidence.getAllResidences().contains(residence1) :
                "La résidence de Rennes ne devrait plus être présente";

        Residence foundResidence = groupResidence.findResidenceByAdress(adress2);
        assert foundResidence != null && foundResidence.equals(residence2) :
                "La résidence de Lannion devrait être trouvée";

        assert groupResidence.findResidenceByAdress(adress1) == null :
                "La recherche d'une résidence supprimée devrait retourner null";

        groupResidence.addResidence(residence2);
        assert groupResidence.getAllResidences().size() == 1 :
                "Le groupe ne devrait pas contenir de doublons";

    }
}