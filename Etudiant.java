public class Etudiant extends Person{

    private String id;
    private String INE;
    private int promo;
    private int[] notes;

    public Etudiant(String id, String name, String surname, int age, String gender, String INE, int promo, int[] notes){
        super(name, surname, gender, age);
        this.id = id;
        this.INE = INE;
        this.promo = promo;
        this.notes = notes;
    }

    public Etudiant(String id, String name, String surname, int age, String gender, String INE, int promo, int[] notes, Contrat contrat){
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

    public int[] getNotes(){
        return this.notes;
    }

    public String toString(){
        String s = " ";
        return super.toString() + s + id + s + INE + s + promo + s + notes; 
    }

    public static void main(String[] args) {
        // Etudiant e = new Etudiant("35qg65cq48", null, null, null, null, null, null, 0)
    }
}