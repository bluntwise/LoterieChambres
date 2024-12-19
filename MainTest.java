import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class AdressTest {

    @Test
    public void testEquals(){
        Adress adress1 = new Adress("Lannion", 22300, "Rue de l'école");
        Adress adress2 = new Adress("Lannion", 22300, "Rue Enssat");

        assertEquals(false, adress1.equals(adress2));
        assertEquals(true, adress1.equals(adress1));
    }

    @Test
    public void testGetCity(){
        Adress adress1 = new Adress("Lannion", 22300, "Rue de l'école");
        Adress adress2 = new Adress("Vannes", 22300, "Rue de l'école");

        assertEquals("Lannion", adress1.getCity());
        assertEquals("Vannes", adress2.getCity());
    }

    @Test
    public void testGetCityCode(){
        Adress adress1 = new Adress("Lannion", 22300, "Rue de l'école");
        Adress adress2 = new Adress("Vannes", 56000, "Rue de l'école");

        assertEquals(22300, adress1.getCityCode());
        assertEquals(56000, adress2.getCityCode());
    }

    @Test
    public void testToString(){
        Adress adress1 = new Adress("Lannion", 22300, "Rue de l'école");
        Adress adress2 = new Adress("Vannes", 56000, "Rue de l'école");

        assertEquals("Rue de l'école Lannion 22300", adress1.toString());
        assertEquals("Rue de l'école Vannes 56000", adress2.toString());
    }
}

class NoteTest {

    @Test
    public void testToString(){
        int[] notesRaw = {15,16,17};
        Note notes = new Note(notesRaw);

        assertEquals("[15, 16, 17]", notes.toString());
    }

    @Test
    public void testGetAverage(){
        int[] notesRaw = {15,16,17};
        Note notes = new Note(notesRaw);

        assertEquals(16, notes.getAverage());
    }

    @Test
    public void testGetNotes() {
        int[] notesRaw = {15, 16, 17};
        Note notes = new Note(notesRaw);
        assertArrayEquals(notesRaw, notes.getNotes());
    }
}

class ContratTest {

    @Test
    public void testGetContrat(){
        Contrat contrat = new Contrat();
        assertEquals("Pas de contrat", contrat.getContrat());

        Contrat contrat2 = new Contrat("4654654654","9-17");
        assertEquals("4654654654", contrat2.getContrat());
    }

    @Test
    public void testGetWorkingHours(){
        Contrat contrat = new Contrat();
        String[] expected = {};
        assertArrayEquals(expected, contrat.getWorkingHours());

        Contrat contrat2 = new Contrat("1293U91","9-17");
        expected = new String[]{"9-17"};
        assertArrayEquals(expected, contrat2.getWorkingHours());

        Contrat contrat3 = new Contrat("1293U91", expected);
        assertArrayEquals(expected, contrat3.getWorkingHours());
    }

    @Test
    public void testToString(){
        Contrat contrat = new Contrat("1293U91","9-17");
        assertEquals("1293U91 [9-17]", contrat.toString());
    }

    @Test
    public void testGetTotalHours(){
        Contrat contrat = new Contrat("1293U91","8-10,17-21");
        assertEquals(6, contrat.getTotalHours());
    }
}

class ChambreTest {

    @Test
    public void testToString(){
        Note note = new Note(new int[]{4, 3});
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, note);
        assertEquals("qsjmklgh4s A101 25.5 2015 2020 5 " + note.toString() + " " + note.getAverage(), chambre.toString());
    }

    @Test
    public void testGetAverage(){
        Note note = new Note(new int[]{4, 3});
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, note);
        assertEquals(3.5, chambre.getAverage());
    }

    @Test
    public void testGetId(){
        Note note = new Note(new int[]{4, 3});
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, note);
        assertEquals("qsjmklgh4s", chambre.getId());
    }

    @Test
    public void testGetSurface() {
        Note note = new Note(new int[]{4, 3});
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, note);
        assertEquals(25.5f, chambre.getSurface(), 0.01f);
    }

    @Test
    public void testGetCreationDate() {
        Note note = new Note(new int[]{4, 3});
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, note);
        assertEquals(2015, chambre.getCreation_date());
    }

    @Test
    public void testGetLatestRenovationDate() {
        Note note = new Note(new int[]{4, 3});
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, note);
        assertEquals(2020, chambre.getLatest_renovation_date());
    }

    @Test
    public void testGetNbLocations() {
        Note note = new Note(new int[]{4, 3});
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, note);
        assertEquals(5, chambre.getNb_locations());
    }

    @Test
    public void testGetScores() {
        Note note = new Note(new int[]{4, 3});
        Chambre chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, note);
        assertEquals(note, chambre.getScores());
    }
}

class EtudiantTest {

    private Etudiant etudiant1;
    private Etudiant etudiant2;
    private Note notes1;
    private Note notes2;
    private Contrat contrat;

    @BeforeEach
    public void setUp() {
        notes1 = new Note(new int[]{15, 16, 17});
        notes2 = new Note(new int[]{2, 3, 4});
        contrat = new Contrat("1234", new String[]{"9-17"});
        etudiant1 = new Etudiant("E123", "Alan", "DELY", 22, "M", "INE12345", 2025, notes1);
        etudiant2 = new Etudiant("E124", "Lisa", "ROGER", 21, "F", "INE12346", 2024, notes2, contrat);
    }

    @Test
    public void testGetId() {
        assertEquals("E123", etudiant1.getId());
    }

    @Test
    public void testGetINE() {
        assertEquals("INE12345", etudiant1.getINE());
    }

    @Test
    public void testGetPromo() {
        assertEquals(2025, etudiant1.getPromo());
    }

    @Test
    public void testGetNotes() {
        assertEquals(notes1, etudiant1.getNotes());
    }

    @Test
    public void testToString() {
        String expected = "Alan DELY M 22 Pas de contrat [] E123 INE12345 2025 " + notes1 + " 18.0";
        assertEquals(expected, etudiant1.toString());
    }

    @Test
    public void testGetAverage() {
        assertEquals(16.0f, etudiant1.getAverage(), 0.01f);
    }

    @Test
    public void testGetPointsWithoutContrat() {
        assertEquals(2027 - 2025 + 16, etudiant1.getPoints(), 0.01f);
    }

    @Test
    public void testGetPointsWithContrat() {
        assertEquals(2027 - 2024 + 3 + contrat.getTotalHours(), etudiant2.getPoints(), 0.01f);
    }
}

class PersonneTest {

    private Personne personne1;
    private Personne personne2;
    private Contrat contrat;

    @BeforeEach
    public void setUp() {
        contrat = new Contrat("1234", new String[]{"9-17"});
        personne1 = new Personne("Ilan", "Chasse", "M", 22);
        personne2 = new Personne("Ilan", "Chasse", "M", 22, contrat);
    }

    @Test
    public void testGetName() {
        assertEquals("Ilan", personne1.getName());
    }

    @Test
    public void testGetSurname() {
        assertEquals("Chasse", personne1.getSurname());
    }

    @Test
    public void testGetGender() {
        assertEquals("M", personne1.getGender());
    }

    @Test
    public void testGetAge() {
        assertEquals(22, personne1.getAge());
    }

    @Test
    public void testGetContratWithoutContrat() {
        assertEquals("Pas de contrat", personne1.getContrat().getContrat());
    }

    @Test
    public void testGetContratWithContrat() {
        assertEquals("1234", personne2.getContrat().getContrat());
    }

    @Test
    public void testToString() {
        String expected = "Ilan Chasse M 22 Pas de contrat []";
        assertEquals(expected, personne1.toString());
    }

    @Test
    public void testGetAverage() {
        assertEquals(0.0f, personne1.getAverage());
    }

    @Test
    public void testGetPointsWithoutContrat() {
        assertEquals(0f, personne1.getPoints());
    }

    @Test
    public void testGetPointsWithContrat() {
        assertEquals(contrat.getTotalHours(), personne2.getPoints());
    }
}

class ResidenceTest {

    private Residence residence;
    private Adress adress;
    private Chambre chambre;
    private Personne personne;

    @BeforeEach
    public void setUp() {
        adress = new Adress("Rennes", 35000, "Rue Exemple");
        residence = new Residence(adress);
        chambre = new Chambre("qsjmklgh4s", "A101", 25.5f, 2015, 2020, 5, new Note(new int[]{4, 3}));
        personne = new Personne("Ilan", "Chasse", "M", 22);
    }

    @Test
    public void testAddPersonneAndChambre() {
        residence.addPersonneAndChambre(personne, chambre);
        assertEquals(personne, residence.getAssociations().get(chambre));
    }

    @Test
    public void testAddChambre() {
        residence.addChambre(chambre);
        assertNull(residence.getAssociations().get(chambre));
    }

    @Test
    public void testDeletePersonne() {
        residence.addPersonneAndChambre(personne, chambre);
        residence.deletePersonne(personne);
        assertNull(residence.getAssociations().get(chambre));
    }

    @Test
    public void testDeleteChambre() {
        residence.addChambre(chambre);
        residence.deleteChambre(chambre);
        assertFalse(residence.getAssociations().containsKey(chambre));
    }

    @Test
    public void testToString() {
        residence.addPersonneAndChambre(personne, chambre);
        String expected = "\n\tResidence : Rue Exemple Rennes 35000\n\nA101 3.5 -> Ilan Chasse 0.0\n";
        assertEquals(expected, residence.toString());
    }

    @Test
    public void testGetAssociations() {
        residence.addPersonneAndChambre(personne, chambre);
        assertTrue(residence.getAssociations().containsKey(chambre));
    }

    @Test
    public void testGetAdress() {
        assertEquals(adress, residence.getAdress());
    }
}
