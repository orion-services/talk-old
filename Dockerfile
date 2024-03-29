FROM open-liberty:21.0.0.5-full-java11-openj9

LABEL \
    org.opencontainers.image.authors="Rodrigo Prestes Machado" \
    description="This image for Orion Talk Service"

# Waiting a new server.xml feature to use a better solution
# https://github.com/OpenLiberty/ci.maven/issues/705
COPY --chown=1001:0 src/main/liberty/config/resources/mysql-connector-java-8.0.19.jar /config/resources/

# Sending Liberty configuration
COPY --chown=1001:0 src/main/liberty/config/* /config/

# Deploy the application
COPY --chown=1001:0 target/orion-talk-service.war /config/dropins/

# Executing
RUN configure.sh