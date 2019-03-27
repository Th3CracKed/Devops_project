[![Build Status](https://travis-ci.com/Th3CracKed/Devops_project.svg?token=Ua5Bde4zpdwh2oEzqAWq&branch=master)](https://travis-ci.com/Th3CracKed/Devops_project)
[![Build Status](	https://img.shields.io/github/license/:user/:repo.svg)](https://github.com/Th3CracKed/Devops_project/blob/master/LICENSE.txt)
# Devops_project

**Feedback :**   
Problèmes :  
1- Probleme configuration Travis  avec gradle a cause de permission d'execution sur le fichier gradlew   
solution :  promouvoir la permission avec git update-index --chmod=+x gradlew  
ou  ajouter dans le fichier de configuration de travis la règle suivante :
before_install:  
 -chmod +x gradlew
