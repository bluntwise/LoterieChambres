public class Personne {
    /*
        classe qui modélise une personne selon un nom, surname, un genre, ...
     */
    private String name;
    private String surname;
    private String gender;
    private int age;
    private Contrat contrat;

    public Personne(String name, String surname, String gender, int age){
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        contrat = null;
    }

    public Personne(String name, String surname, String gender, int age, Contrat contrat){
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.contrat = contrat;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public String getGender(){
        return this.gender;
    }

    public int getAge(){
        return this.age;
    }

    public Contrat getContrat(){
        /*
            méthode qui renvoie le contrat
         */
        if (this.contrat == null){
            return new Contrat();
        }
        return this.contrat;
    }

    public String toString(){
        /*
            méthode qui affiche la personne avec ses caractéristiques
         */
        String s = " ";
        return name + s + surname + s + gender + s + age + s + getContrat();
    }

    public float getAverage(){
        return 0.0f;
    }

    public float getPoints(){
        /*
            méthode qui renvoie les points de la personne
         */
        if (getContrat() != null){
            return getContrat().getTotalHours();
        }else{
            return 0f;
        }
    }

}