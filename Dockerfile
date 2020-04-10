# Base Image
FROM containers.cisco.com/aws_managed/tomcat-7-jws3.0_custom:latest

## Maintainer ##
MAINTAINER Lae2Cae-Operations
 
USER root
EXPOSE 8080
 
COPY **/repo  /home/jboss/lae-home/app-root/runtime/repo
RUN chmod -R 777 /home/jboss/lae-home/app-root/runtime/repo
 
 # Add Deployment WAR
COPY **/**/**/**/apix.war  /opt/webserver/webapps/
 
RUN echo "Building Application Image!"
 
# Main Command
CMD ["/opt/webserver/bin/serverStart.sh"]


