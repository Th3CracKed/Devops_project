[![Build Status](https://travis-ci.com/Th3CracKed/Devops_project.svg?token=Ua5Bde4zpdwh2oEzqAWq&branch=master)](https://travis-ci.com/Th3CracKed/Devops_project)
[![Build Status](	https://img.shields.io/github/license/:user/:repo.svg)](https://github.com/Th3CracKed/Devops_project/blob/master/LICENSE.txt)
# Devops_project

**Couverture du code :**

Pour vérifier la couverture du code, veuillez vous rendre dans build -> reports -> jacoco 

**Tests :**

Pour pouvoir tester les methodes privées, au début on a utilisé Java reflection mais le problème si on fait un refactoring de la méthode utiliser il faut penser a changer ca dans les tests.
On a donc utiliser Manifold un framework qui permet d'enrechir java avec plein de nouveaux fonctionnalitées.
(A voir dans la DataFramePrintTest -> test_Find_Max_1)

Manifold permet aussi de faire des extensions de classes cad qu'on peut ajouter des methodes qui seront utiles pour tester une classe lambda sans ajouter du code directement dans cette classe.
Plus de details : http://manifold.systems/docs.html#self--extensions

**Feedback :**   
Gradle Problèmes :  
1- Problème de configuration Travis avec gradle à cause de permission d'execution sur le fichier gradlew   
solution :  promouvoir la permission avec git update-index --chmod=+x gradlew  
ou  ajouter dans le fichier de configuration de travis la règle suivante :

before_install:  
 -chmod +x gradlew
 
 2- Pour créer un jar file il faut spécifier le fichier java d'entrée et inclure tout les bibliothèques (fat jar)
 
 **Information sur le parser de fichiers .csv :**  
  - Les éléments doivent être séparés par des virgules.
  Voici un exemple simple de fichier .csv :

 Nom,Prenom,Pseudo,Age

 Tommy,Sulivan,Kurapika,12

 Younes,Leboss,fayherinn,52

 Isabella,Ray,thePNeverland,45

 Phoenix,Right,Ace_Attorney,21

 Ce fichier ne précise pas de colonne d'index, les indexes seront donc automatiquement générés en "0,1,2,3"

 Autrement, on peut préciser le nom des indexes avec la colonne de nom "Index", obligatoirement au début du fichier CSV.
 Cette colonne sera évidemment supprimée pendant le parsing.

 Index,Nom,Prenom,Pseudo

 0,Tommy,Sulivan,Kurapika

 1,Younes,Leboss,fayherinn

 2,Isabella,Ray,thePNeverland

 3,Phoenix,Right,Ace_Attorney
 
 **Docker :**
 
 https://hub.docker.com/r/th3cracked/devops_project/
On a utiliser une image docker avec java preinstaller, dans le Dockerfile on build l'application.
Pour dérouler un scénario :
docker run -it th3cracked/devops_project scenario
Pour lancer les tests : 
docker run -it th3cracked/devops_project
si vous voulez lancer linux pour voir la structure des fichiers
 
 
