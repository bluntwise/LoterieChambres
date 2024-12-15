import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSVChambre {
    private String id;
    private String name;
    private String residence;
    private String address;
    private String city;
    private int city_code;
    private float surface;
    private int creation_date;
    private int latest_renovation_date;
    private int nb_locations;
    private int[] scores;

    public ReadCSVChambre(String[] line_splited) {
        // just filling variables from the table
        this.id = line_splited[0];
        this.name = line_splited[1];
        this.residence = line_splited[2];
        this.address = line_splited[3].replace("\"",""); // had to convert to int
        this.city = line_splited[4].replace("\"","");
        this.city_code = Integer.parseInt(line_splited[5]);
        this.surface = Float.parseFloat(line_splited[6]);
        this.creation_date = Integer.parseInt(line_splited[7]);
        this.latest_renovation_date = Integer.parseInt(line_splited[8]);
        this.nb_locations = Integer.parseInt(line_splited[9]);
        this.scores = ReadCSVChambre.parse_list_int(line_splited[10]);
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
        System.out.println(this.residence);
        System.out.println(this.address);
        System.out.println(this.city);
        System.out.println(this.city_code);
        System.out.println(this.surface);
        System.out.println(this.creation_date);
        System.out.println(this.latest_renovation_date);
        System.out.println(this.nb_locations);
        System.out.println(this.scores);
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getResidence() {
        return residence;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getCity_code() {
        return city_code;
    }

    public float getSurface() {
        return surface;
    }
    public int getCreation_date() {
        return creation_date;
    }

    public int getLatest_renovation_date() {
        return latest_renovation_date;
    }

    public int getNb_locations() {
        return nb_locations;
    }

    public int[] getScores() {
        return scores;
    }

    public static void main(String[] args) {
        String path;
        boolean c = false;
        if (args.length == 0 && c){ 
            System.out.println("This main needs a path!");
        }
        else {
            // path = args[0]; // getting the argument
            path = "Ressources/liste_chambres.csv";
            File csvFile = new File(path); // to read the CSV file
            try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String line;
                ReadCSVChambre obj_line;
                reader.readLine(); // Dropping first line
                while((line = reader.readLine()) != null) { // reader.readLine() -> to get the line and 
                    // System.out.println(line);
                    obj_line = new ReadCSVChambre(line.split(";")); // spliting here but it was a choice
                    obj_line.affiche(); // do terminal print
                }
            } catch (IOException e) {
                System.err.println(e);
                System.err.println("An issue occured...");
            }
        }
    }
}