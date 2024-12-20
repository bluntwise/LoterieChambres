### Alan DELY
## La (fausse) loterie des chambres

Ce projet a pour but de répartir des chambres à des personnes. 
Ces personnes peuvent être des étudiants ou des personnes qui travaillent 
seulement. J'ai choisi de modéliser un groupement de résidences qui rassemble
plusieurs résidences. Ce groupement stocke toutes les chambres et toutes les
demandes des personnes voulant les chambres. 

## Lancement sur Linux

### Déziper le projet
```bash
unzip LoterieChambres.zip
```
### Se placer dans le dossier
```bash
cd LoterieChambres/
```
### Compilage
```bash
java *.java
```
### Lancer Projet
```bash
java GroupResidence
```
### Lancement des tests
```bash
java Main
```
<img src="img.png" alt="Description" width="600" height="600">


## Les Tests

Pour les tests, j'ai modélisé une classe MainTest. Pour chaque classe de mon projet j'ai 
créé une classe de test associée. Ces classes de test se situent dans le fichier MainTest.java et 
instancient des objets de la classe qui est leur associée. Par exemple la classe de test
ResidenceTest va créé un objet Residence et vérifier avec l'instruction assert si le 
résultat de la méthode testée est en acccord avec ce qu'on attend. Si j'avais eu plus de 
temps j'aurais changé mon implentation pour le stockage de mes associations en passant
par une structure qui trie directement en fonction d'une méthode les associations. 




