import java.util.Arrays;

public class Contrat {
    /*
        classe qui modélise un contrat avec un id et des heures de travail
     */
    private String contrat;
    private String[] workinghours;

    public Contrat(String contrat, String[] workinghours){
        this.contrat = contrat;
        this.workinghours = workinghours;
    }

    public Contrat(String contrat, String workinghours){
        this.contrat = contrat;
        this.workinghours = workinghours.split(",");
    }

    public Contrat(){
        this.contrat = "Pas de contrat";
        this.workinghours = new String[0];
    }
    public String getContrat(){
        /*
            méthode qui renvoie l'id du contrat
         */
        return this.contrat;
    }

    public String[] getWorkingHours(){
        /*
            méthode qui renvoie les heures de travail
         */
        return this.workinghours;
    }

    public String toString(){
        /*
            méthode qui affiche le contrat et ses caractéristiques
         */
        return contrat + " " + Arrays.toString(workinghours);
    }

    public float getTotalHours(){
            /*
                méthode qui extrait les heures de travail et fait la total
             */
            float total = 0;
            for (int i = 0; i < workinghours.length; i++) {
                float start = Float.parseFloat(getWorkingHours()[i].split("-")[0].replace("\"",""));
                float end = Float.parseFloat(getWorkingHours()[i].split("-")[1].replace("\"",""));
                
                total += Math.abs(end - start);
            }
                
            
            return total;
    }
}