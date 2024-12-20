import java.util.Arrays;
import java.util.stream.IntStream;

public class Note {
    private int[] notes;

    public Note(int[] notes){
        this.notes = notes;
    }

    public String toString(){
        return Arrays.toString(this.notes);
    }



    public float getAverage(){
        if (getNotes().equals(null)){
            return 0f;
        }else{
            double moyenne = IntStream.of(notes).average().orElse(0.0);
            double moyenneArrondie = Math.round(moyenne * 100.0) / 100.0;
            return (float)moyenneArrondie;
        }
    
    }

    public int[] getNotes(){
        return this.notes;
    }


}