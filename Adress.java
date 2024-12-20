public class Adress {
    /*
        classe qui modélise une adresse avec une ville, un code postal et un rue
     */
    private String city;
    private int city_code;
    private String street;


    public Adress(String city, int city_code, String street){
        this.city = city;
        this.city_code = city_code;
        this.street = street;
    }

    
    public boolean equals(Adress adress){
        /*
            redéfinition d'equals() pour comparer une autre adress selon
            ses attributs et non son adress mémoire
         */
        return this.getCity().equals(adress.getCity()) && this.getCityCode() == adress.getCityCode()  && this.getStreet().equals(adress.getStreet());
    }

    /* Getters */
    
    public String getCity(){
        return this.city;
    }
    public int getCityCode(){
        return this.city_code;
    }

    public String getStreet(){
        return this.street;
    }

    public String toString(){
        /*
            méthode qui renvoie l'adresse
         */
        return this.street + " " + this.city + " " + Integer.toString(this.city_code);
    }
}