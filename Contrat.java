import java.util.Arrays;

public class Contrat {

    private String contrat;
    private String[] workinghours;

    public Contrat(String contrat, String[] workinghours){
        this.contrat = contrat;
        this.workinghours = workinghours;
    }

    public String getContrat(){
        return this.contrat;
    }

    public String[] getWorkinghours(){
        return this.workinghours;
    }

    public String toString(){
        return contrat + " " + Arrays.toString(workinghours);
    }

    public static void main(String[] args) {
        String[] workingHours = "8-10,17-21".split(",");

        Contrat c = new Contrat("8453146484", workingHours);
        System.out.println(c);
    }
}