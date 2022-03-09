FROM openjdk

ADD target/teamManager.jar teamManager.jar

EXPOSE 8097

ENTRYPOINT ["java", "-jar", "teamManager.jar"]