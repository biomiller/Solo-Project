ARG WAR_FILE="TopDeck.war"
ARG WILDFLY_VERSION="10.1.0.Final"

FROM maven:latest AS maven

COPY . .

RUN mvn package

FROM jboss/wildfly:${WILDFLY_VERSION}

COPY --from=maven ./target/${WAR_FILE} /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080
