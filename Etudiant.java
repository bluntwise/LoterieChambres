public class Etudiant extends Personne{
    /*
        classe qui modélise un etudiant avec un id, ine, propo et ses notes
     */
    private String id;
    private String INE;
    private int promo;
    private Note notes;

    public Etudiant(String id, String name, String surname, int age, String gender, String INE, int promo, Note notes){
        super(name, surname, gender, age);
        this.id = id;
        this.INE = INE;
        this.promo = promo;
        this.notes = notes;
    }
    
    public Etudiant(String id, String name, String surname, int age, String gender, String INE, int promo, Note notes, Contrat contrat){
        super(name, surname, gender, age, contrat);
        this.id = id;
        this.INE = INE;
        this.promo = promo;
        this.notes = notes;
    }

    public String getId(){
        return this.id;
    }

    public String getINE(){
        return this.INE;
    }

    public int getPromo(){
        return this.promo;
    }

    public Note getNotes(){
        return this.notes;
    }

    public String toString(){
        /*
            méthode qui renvoie l'état de l'étudiant
         */
        String s = " ";
        return super.toString() + s + id + s + INE + s + promo + s + notes + s + getPoints(); 
    }

    @Override
    public float getAverage(){
        /*
            méthode qui renvoie la moyenne des notes de l'étudiant
         */
        return notes.getAverage();
    }

    public float getPoints(){
        /*
            méthode qui renvoie les points de l'étudiant selon
            un système de points
         */
        float points = notes.getAverage() + (2027 - getPromo());
        if (getContrat() == null){
            return points;
        }
        return points + getContrat().getTotalHours();
    }
}