public class Person {

    private String name;
    private String surname;
    private String gender;
    private int age;
    private Contrat contrat;

    public Person(String name, String surname, String gender, int age){
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        contrat = null;
    }

    public Person(String name, String surname, String gender, int age, Contrat contrat){
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
        return this.contrat;
    }

    public String toString(){
        String s = " ";
        return name + s + surname + s + gender + s + age + s + contrat;
    }

    public float getAverage(){
        return 0;
    }


}