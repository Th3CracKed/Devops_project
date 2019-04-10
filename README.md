[![Build Status](https://travis-ci.com/Th3CracKed/Devops_project.svg?token=Ua5Bde4zpdwh2oEzqAWq&branch=master)](https://travis-ci.com/Th3CracKed/Devops_project)
[![Build Status](	https://img.shields.io/github/license/:user/:repo.svg)](https://github.com/Th3CracKed/Devops_project/blob/master/LICENSE.txt)
# Devops_project
Couverture du code dans build -> reports -> jacoco 

Tests : 
Pour pouvoir tester les methodes privées, au début on a utilisé Java reflection mais le problème est que si on fait un refactoring de la méthode utilisée, il faut penser a changer ça dans les tests.
On a donc utilisé le framework Manifold qui permet d'enrichir java avec plein de nouvelles fonctionnalités.

**Feedback :**   
Gradle Problèmes :  
1- Problème de configuration Travis avec gradle à cause de permission d'execution sur le fichier gradlew   
solution :  promouvoir la permission avec git update-index --chmod=+x gradlew  
ou  ajouter dans le fichier de configuration de travis la règle suivante :

before_install:  
 -chmod +x gradlew
