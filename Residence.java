import java.util.LinkedHashMap;
import java.util.Map;

public class Residence{

    private Adress adress;
    private Map<Chambre,Personne> associations;
    public Residence(Adress adress){
        this.adress = adress;
        associations = new LinkedHashMap<>();
    }

    public void addPersonneAndChambre(Personne personne, Chambre chambre){
        associations.put(chambre, personne);
    }

    public void addChambre(Chambre chambre){
        associations.put(chambre, null);
    }

    public void deletePersonne(Personne personne){
        Chambre chambre = null;
        for (Map.Entry<Chambre, Personne> entry : associations.entrySet()) {
            if (entry.getValue().equals(personne)) {
                chambre = entry.getKey();  // Retourne la personne associée
            }
        }associations.put(chambre,null);
    }

    public void deleteChambre(Chambre chambre){
        associations.remove(chambre);
    }

    public String toString(){
        String r = "\n\tResidence : " + getAdress() + "\n\n";
        Chambre c;
        Personne p;
        for (Map.Entry<Chambre,Personne> entry : associations.entrySet()) {
            c = entry.getKey();
            p = entry.getValue();
            r += c.getName() + " " + c.getAverage() + " -> ";
            if (p != null){
                r += p.getName() + " " + p.getSurname() + " " + p.getPoints() + "\n";
            }else{
                r += "Pas d'etudiant\n";
            }
        }
        return r ;
    }



    /*Getters */

    public Map<Chambre,Personne> getAssociations(){
        return associations;
    }
    public Adress getAdress(){
        return this.adress;
    }
}