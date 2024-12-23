import java.util.LinkedHashMap;
import java.util.Map;

public class Residence{
    /*
        classe qui modélise une Residence avec des associations
        chambres et personnes avec une adresse
     */
    private Adress adress;
    private Map<Chambre,Personne> associations;
    public Residence(Adress adress){
        this.adress = adress;
        associations = new LinkedHashMap<>();
    }

    public void addPersonneAndChambre(Personne personne, Chambre chambre){
        /*
            méthode qui ajoute une association chambre personne
         */
        associations.put(chambre, personne);
    }

    public void addChambre(Chambre chambre){
        /*
            méthode qui ajoute une chambre
         */
        associations.put(chambre, null);
    }

    public void deletePersonne(Personne personne){
        /*
            méthode qui supprime une personne dans la résidence et une chambre
         */
        Chambre chambre = null;
        for (Map.Entry<Chambre, Personne> entry : associations.entrySet()) {
            if (entry.getValue().equals(personne)) {
                chambre = entry.getKey();  // Retourne la personne associée
            }
        }associations.put(chambre,null);
    }

    public void deleteChambre(Chambre chambre){
        /*
            méthode qui supprime une chambre dans les associations
         */
        associations.remove(chambre);

    }

    public String toString(){
        /*
            méthode qui renvoie les associations chambres personnes
         */
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
        /*
            méthode qui renvoie les associations
         */
        return associations;
    }
    public Adress getAdress(){
        /*
            méthode qui renvoie l'adresse de la résidence
         */
        return this.adress;
    }
}