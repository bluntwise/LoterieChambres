import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVEtudiant {
    private String id;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private int INE;
    private int promo;
    private int[] notes;
    private String contrat;
    private String[] working_hours;

    public ReadCSVEtudiant(String[] line_splited) {
        // just filling variables from the table
        this.id = line_splited[0];
        this.name = line_splited[1];
        this.surname = line_splited[2];
        this.age = Integer.parseInt(line_splited[3]); // had to convert to int
        this.gender = line_splited[4];
        // 'if' one line statements, just to verify if the value is "null"
        this.INE = ((line_splited[5].equals("null")) ? 0 : Integer.parseInt(line_splited[5])); 
        this.promo = ((line_splited[6].equals("null")) ? 0 : Integer.parseInt(line_splited[6]));
        this.notes = ((line_splited[7].equals("null")) ? new int[0] : parse_list_int(line_splited[7]));
        this.contrat = ((line_splited[8].equals("null")) ? "null" : line_splited[8]);
        this.working_hours = ((line_splited[9].equals("null")) ? new String[0] : line_splited[9].split(","));
    }

    private static int[] parse_list_int(String notes) {
        // function to convert the string of notes to a list of int
        String[] n = notes.replace("[", "").replace("]", "").split(",");
        int[] res = new int[n.length];
        for (int i = 0; i<n.length; i++){
            // System.out.println(n[i]);
            res[i] = Integer.parseInt(n[i]);
        }
        return res;
    }

    public void affiche() {
        System.out.println(this.id);
        System.out.println(this.name);
        System.out.println(this.surname);
        System.out.println(this.age);
        System.out.println(this.gender);
        System.out.println(this.INE);
        System.out.println(this.promo);
        System.out.println(this.notes);
        System.out.println(this.contrat);
        System.out.println(this.working_hours);
    }

    public static void main(String[] args) {
        String path;
        if (args.length == 0){ 
            System.out.println("This main needs a path!");
        }
        else {
            path = args[0]; // getting the argument
            File csvFile = new File(path); // to read the CSV file
            try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String line;
                ReadCSVEtudiant obj_line;
                reader.readLine(); // Dropping first line
                while((line = reader.readLine()) != null) { // reader.readLine() -> to get the line and 
                    // System.out.println(line);
                    obj_line = new ReadCSVEtudiant(line.split(";")); // spliting here but it was a choice
                    obj_line.affiche(); // do terminal print
                }
            } catch (IOException e) {
                System.err.println(e);
                System.err.println("An issue occured...");
            }
        }
    }
}