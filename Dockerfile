FROM openjdk:12.0.1-jdk-oraclelinux7

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x entrypoint.sh
COPY target/app.jar /app/app.jar

ENV MAX_MEM=256M

ENTRYPOINT ["/entrypoint.sh"]

