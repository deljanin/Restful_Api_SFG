FROM openjdk:19

# Install MySQL server
#RUN apt-get update && \
#   apt-get install -y mysql-server
#RUN apt-get update -y
#
#RUN apt-get install openjdk-18-jre-headless -y

# Copy application JARs
COPY ./target/restfulApiSFG-0.0.1-SNAPSHOT.jar /app/restApp.jar

# Expose ports
EXPOSE 443

CMD java -jar /app/restApp.jar