FROM java:8

ARG PROJECT_NAME=Devops_project

COPY . /${PROJECT_NAME}

RUN cd /${PROJECT_NAME} && ./gradlew clean && \
    cd /${PROJECT_NAME} && ./gradlew build

RUN cp ${PROJECT_NAME}/build/libs/${PROJECT_NAME}-1.0.jar ${PROJECT_NAME}-1.0.jar

COPY dockerScript.sh dockerScript.sh

RUN ["chmod", "+x", "dockerScript.sh"]

ENTRYPOINT ["./dockerScript.sh"]