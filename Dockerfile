FROM maven:3.8.4-openjdk-17

LABEL maintainer="martynas@atomgraph.com"

COPY . /usr/src/JSON2XML

WORKDIR /usr/src/JSON2XML

RUN mvn clean install

### entrypoint

ENTRYPOINT ["java", "-jar", "target/json2xml-jar-with-dependencies.jar"]