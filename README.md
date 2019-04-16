[![Build Status](https://travis-ci.com/Th3CracKed/Devops_project.svg?token=Ua5Bde4zpdwh2oEzqAWq&branch=master)](https://travis-ci.com/Th3CracKed/Devops_project)
[![license](	https://img.shields.io/github/license/:user/:repo.svg)](https://github.com/Th3CracKed/Devops_project/blob/master/LICENSE.txt)
[![license](https://img.shields.io/badge/license-GPL%203.0-yellowgreen.svg)](https://github.com/Th3CracKed/Devops_project/blob/master/LICENSE.txt)
# Devops_project

## Liste des fonctionnalitées (toute celle proposées dans le sujet avec celles qui sont optionnelles ) :

**4 Constructeurs de DataFrame :**
- Avec des Lists. 
- Avec des array et Vargs pour les colonnes
- Avec un parseur CSV
- Avec un parseur ( Version dependency injection [Voir ci-dessous](#Dependency injection))

**Affichage :**
- PrintAll
- PrintHead
- PrintHead(int nbLine);
- PrintTail
- PrintTail(int nbLine);

**Sélection dans un dataFrame :**

Créer un nouveau dataframe en sélectionnant un sous-ensemble des donnéees d’un dataframe existant.

- Sous-ensemble de lignes à partir de leur index.

- Sous-ensemble de lignes à partir de leur labels

**Statistiques :**

Chaque méthode prend soit un axis (ligne ou colonne), soit prend un axis et un boolean pour skiper les valeurs null ou non, soit ne prend pas de paramètre (comportement par défault par ligne et skip null)
 - Sum
 - Min
 - Max
 - Average

**Groupe By et Aggregate :**
Les deux fonctionnalités sont implémentées dans la même méthode : groupByAgregate.
Le premier argument est un String qui permet de choisir la colonne sur laquelle on va vérifier les occurences sur lesquelles on va effectuer des opérations, le deuxième argument correspond à l'opération à effectuer : sum,prod,min,max (somme, produit, min, max)
Voici un exemple d'utilisation :
> DataFrame d = dataframe_courses.groupByAgregate("client_name","min");

Cette ligne permet de créer un dataframe d qui contient le minimum d'achat de chaque client pour un dataframe contenant une liste de courses contenant un prix. <br>
*dataframe_courses*
>  client_name   product_name  price <br>
0       Tommy           ps4   300 <br>
1       Robin  calculatrice    20 <br>
2       Tommy   micro-ondes 64.25 <br>
3       Tommy        miroir    25 <br>
4       Fayhe seche-cheveux    45 <br>

Résultat après le groupBy

>  client_name      product_name      price <br>
0       Tommy  ps4 micro-ondes miroir  25.0 <br> 
1       Robin            calculatrice  20.0 <br>
2       Fayhe           seche-cheveux  45.0 


**Couverture du code :**

Pour vérifier la couverture du code, veuillez vous rendre dans build -> reports -> jacoco 

## Tests :

Pour pouvoir tester les methodes privées, au début on a utilisé Java reflection mais le problème si on fait un refactoring de la méthode utiliser il faut penser a changer ca dans les tests.
On a donc utiliser **Manifold** un framework qui permet d'enrechir java avec plein de nouveaux fonctionnalitées.
(A voir dans la DataFramePrintTest -> test_Find_Max_1)

**Manifold** permet aussi de faire des extensions de classes cad qu'on peut ajouter des methodes qui seront utiles pour tester une classe lambda sans ajouter du code directement dans cette classe.
Plus de details : http://manifold.systems/docs.html#self--extensions
 
 ## Docker :
 
 https://hub.docker.com/r/th3cracked/devops_project/
On a utiliser une image docker avec java preinstaller, dans le Dockerfile on build l'application.
Pour pouvoir lancer les tests et voir le scénario sur la même image, on a créer un script shell.

**Pour dérouler un scénario :**
docker run -it th3cracked/devops_project scenario

**Pour lancer les tests :**
docker run -it th3cracked/devops_project test

**Si vous voulez lancer linux pour voir la structure des fichiers :** docker run -it th3cracked/devops_project 
  
## Dependency injection :
 
 On a utilisé Dagger2 qui permet de respecter le 'Open / Closed principle' et permet aussi d'avoir des tests unitaire plus propre.
 Cette partie est juste pour experimenter et n'est ajoutée qu'à la fin dans un package séparé (la couverture du code dans cette partie n'est pas à 100% puisque Dagger genère des classes qui font l'injection).
 
 On a utilisé Dagger sur un constructeur de Column et un autre de DataFrame sans modifier l'interface => une manière simple de faire l'injection de dépendance et d'annoter les constructeur avec @Inject mais nous avons choisi de experimenter avec l'injection de dépendance comme si on avait pas l'accès au code comme dans le cas d'une bibliothèque, ce qui est plus dur a réaliser.
 
 
 ## Feedback :
    
 **Gradle Problèmes :**
   
 1- Problème de configuration Travis avec gradle à cause de permission d'execution sur le fichier gradlew   
 
 *Solution :*  
 promouvoir la permission avec git update-index --chmod=+x gradlew  
 ou  ajouter dans le fichier de configuration de travis la règle suivante :

`before_install: 
 -chmod +x gradlew`
  
  2- Pour créer un jar file il faut spécifier le fichier java d'entrée et inclure tout les bibliothèques (fat jar)
  
  **Docker Problèmes :**
   
  - Le temps de création d'images lors du débuggage des problèmes sur docker est insupportable (trop long).
   
  - Un fichier script shell créer sur windows n'est pas compatible avec linux et ce bug nous a pris énormement du temps à résoudre. 
