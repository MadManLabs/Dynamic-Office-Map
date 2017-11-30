FROM tomcat:latest
MAINTAINER Florin Stancu (stancu.florin23@gmail.com)

RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY DOMServer/web-client/target/web-client.war /usr/local/tomcat/webapps/ROOT.war

COPY DOMServer/server/target/server-api.war /usr/local/tomcat/webapps/api.war

COPY properties/*.properties /usr/local/tomcat/conf/