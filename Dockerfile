FROM topoo/java:jdk-8-u202

#Locale
ENV LANG=en_US.UTF-8  \
    APP_NAME=springboot-demo-0.0.1-SNAPSHOT.jar  \
    PROFILE=dev  \
    JAVA_OPTS=""

RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo Asia/Shanghai > /etc/timezone

#TODO tmp volume for spring
VOLUME /tmp

RUN mkdir -p /data/media

RUN  mkdir -p /opt/springboot-demo/config && mkdir -p /opt/springboot-demo/src/main/webapp

ADD target/${APP_NAME} /opt/springboot-demo
#ADD config/ /opt/gps-web/config
#ADD src/main/webapp/ /opt/springboot-demo/src/main/webapp

#Make file modification time, which impact if modification time reqired for web apps
#RUN sh -c “touch /opt/gps-web/${APP_NAME}”

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "cd /opt/springboot-demo && java $JAVA_OPTS -Dspring.profiles.active=$PROFILE -Djava.security.egd=file:/dev/./urandom -jar $APP_NAME" ]