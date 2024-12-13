public class Adress {

    private String city;
    private int city_code;
    private String street;

    public Adress(String city, int city_code, String street){
        this.city = city;
        this.city_code = city_code;
        this.street = street;
    }

    
    public boolean equals(Adress adress){
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
        return this.street + " " + this.city + " " + Integer.toString(this.city_code);
    }

    
    public static void main(String[] args) {
        
        Adress a = new Adress("Lannion", 22300, "46 Avenue du Général de Gaulle");
        System.out.println(a);
    }
}