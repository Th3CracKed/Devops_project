[![Build Status](https://travis-ci.com/Th3CracKed/Devops_project.svg?token=Ua5Bde4zpdwh2oEzqAWq&branch=master)](https://travis-ci.com/Th3CracKed/Devops_project)
[![Build Status](	https://img.shields.io/github/license/:user/:repo.svg)](https://github.com/Th3CracKed/Devops_project/blob/master/LICENSE.txt)
# Devops_project

**Couverture du code**
Pour vérifier la couverture du code, veuillez vous rendre dans build -> reports -> jacoco 

**Tests**
Pour pouvoir tester les méthodes privées, nous avons au débur utilisé Java reflection mais le problème est que si on fait un refactoring de la méthode utilisée, il faut penser a changer ça dans les tests.
On a donc utilisé le framework Manifold qui permet d'enrichir java avec plein de nouvelles fonctionnalités.

**Feedback :**   
Gradle Problèmes :  
1- Problème de configuration Travis avec gradle à cause de permission d'execution sur le fichier gradlew   
solution :  promouvoir la permission avec git update-index --chmod=+x gradlew  
ou  ajouter dans le fichier de configuration de travis la règle suivante :

before_install:  
 -chmod +x gradlew
 
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
 
