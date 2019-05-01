FROM maven:3.6.0-jdk-8

LABEL maintainer="martynas@atomgraph.com"

COPY . /usr/src/JSON2XML

WORKDIR /usr/src/JSON2XML

RUN mvn clean install

### entrypoint

ENTRYPOINT ["java", "-jar", "target/json2xml-1.0.2-SNAPSHOT-jar-with-dependencies.jar"]