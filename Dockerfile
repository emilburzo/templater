FROM jetty:9-jdk8

ADD templater-server/target/templater-server-1.0-SNAPSHOT.war webapps/ROOT.war

HEALTHCHECK CMD curl --fail http://localhost:8080/templater.css || exit 1
