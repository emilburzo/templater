FROM jetty:latest

ADD target/templater-server-1.0-SNAPSHOT.war webapps/ROOT.war
