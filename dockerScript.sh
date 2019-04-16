#!/bin/bash
if [ "$1" == "test" ]
then cd Devops_project && ./gradlew test
elif [ "$1" == "scenario" ]
then java -jar /Devops_project-1.0.jar
else /bin/bash
fi
