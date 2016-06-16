FROM jetty:latest

ADD templater-server/target/templater-server-1.0-SNAPSHOT.war webapps/ROOT.war
