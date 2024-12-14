import java.util.Arrays;
import java.util.stream.IntStream;
import java.lang.*;

public class Note {
    private int[] notes;

    public Note(int[] notes){
        this.notes = notes;
    }

    public String toString(){
        return Arrays.toString(this.notes);
    }

    public static void main(String[] args) {

    }



    public float getAverage(){
        if (getNotes().equals(null)){
            return 0f;
        }else{
            return IntStream.of(notes).sum()/getNotes().length;
        }
    
    }

    public int[] getNotes(){
        return this.notes;
    }


}