import java.util.Arrays;
import java.util.stream.IntStream;
import java.lang.*;

public class Note {
    private int[] notes;

    public Note(int[] notes){
        this.notes = notes;
    }

    public int getMoyenne(){
        int s = 0;
        for (int i = 0; i < notes.length; i++) {
            s += notes[i];
        }
        return s/notes.length;
    }

    public String toString(){
        return Arrays.toString(this.notes);
    }

    public static void main(String[] args) {

    }

    public float getAverage(){
        return IntStream.of(notes).sum()/notes.length;
    }
}