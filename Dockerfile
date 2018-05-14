FROM douglasffilho/compliance-alpine
MAINTAINER douglasf.filho@gmail.com
COPY build/libs/hmtf-user-services-1.1.1.jar /tmp/HelpMeToFood-user-services.jar
EXPOSE 8080/tcp
