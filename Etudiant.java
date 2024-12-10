public class Etudiant extends Person{

    private String id;
    private String INE;
    private String promo;
    private int[] notes;

    public Etudiant(String id, String name, String surname, int age, String gender, String INE, String promo, int[] notes){
        super(name, surname, gender, age);
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

    public String getPromo(){
        return this.promo;
    }

    public int[] getNotes(){
        return this.notes;
    }


    public static void main(String[] args) {
        // Etudiant e = new Etudiant("35qg65cq48", null, null, null, null, null, null, 0)
    }
}