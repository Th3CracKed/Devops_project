FROM fabric8/java-jboss-openjdk8-jdk:1.4.0

ENV JAVA_APP_JAR java-container.jar
ENV AB_OFF true

EXPOSE 8080

ADD target/$JAVA_APP_JAR /deployments/