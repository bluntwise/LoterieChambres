public class Chambre {
    private String id;
    private String name;
    private Residence residence;
    private float surface;
    private int creation_date;
    private int latest_renovation_date;
    private int nb_locations;
    private Note scores;

    public Chambre(String id, String name, Residence residence, float surface, int creation_date, int latest_renovation_date, int nb_locations, Note scores){
        this.id = id;
        this.name = name;
        this.residence = residence;
        this.surface = surface;
        this.creation_date = creation_date;
        this.latest_renovation_date = latest_renovation_date;
        this.nb_locations = nb_locations;
        this.scores = scores;
    }

    public String toString(){
        String s = " ";
        return getId() + s + getName() + s + residence.toString() + s + surface + s + creation_date + s + latest_renovation_date + s + nb_locations + s + scores.toString() + s + getAverage();  

    }

    public float getAverage(){
        return scores.getAverage();
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }

    public Residence getResidence(){
        return this.residence;
    }

    public float getSurface(){
        return this.surface;
    }

    public int getCreation_date(){
        return this.creation_date;
    }

    public int getLatest_renovation_date(){
        return this.latest_renovation_date;
    }

    public int getNb_locations(){
        return this.nb_locations;
    }

    public Note getScores(){
        return this.scores;
    }

    public static void main(String[] args) {
        
    }
}

