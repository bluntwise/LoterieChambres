import java.util.Arrays;
import java.util.stream.IntStream;

public class Note {
    /*
        classe qui modélise des notes
     */
    private int[] notes;

    public Note(int[] notes){
        this.notes = notes;
    }

    public String toString(){
        /*
            méthode qui affiche les notes
         */
        return Arrays.toString(this.notes);
    }



    public float getAverage(){
        /*
            méthode qui renvoie la moyenne des notes
         */
        if (getNotes().equals(null)){
            return 0f;
        }else{
            double moyenne = IntStream.of(notes).average().orElse(0.0);
            double moyenneArrondie = Math.round(moyenne * 100.0) / 100.0;
            return (float)moyenneArrondie;
        }
    
    }

    public int[] getNotes(){
        /*
            méthode qui renvoie le tableau de notes
         */
        return this.notes;
    }


}