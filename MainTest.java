
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdressTest {

    public static void main(String[] args){
        System.out.println("alan");
    }

    @Test
    public void testEquals(){
        Adress adress1 = new Adress("Lannion", 22300, "Rue de l'école");
        Adress adress2 = new Adress("Lannion", 22300, "Rue Enssat");

        assertEquals(adress1.equals(adress2),false);
        assertEquals(adress1.equals(adress1),true);
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
    public void testtoString(){
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

        assertEquals(16,notes.getAverage());
    }

    @Test
    public void testGetNotes() {
        int[] notesRaw = {15, 16, 17};
        Note notes = new Note(notesRaw);
        assertEquals(notesRaw, notes.getNotes());
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
        
        Contrat contrat3 = new Contrat("1293U91",expected;
        assertArrayEquals(expected, contrat3.getWorkingHours());

    }

    @Test
    public void testToString(){

    }
}