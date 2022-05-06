FROM ncr-csp-docker-snapshots.jfrog.io/tx-apitoolkit-service:2.4.0-latest
RUN mkdir /opt/tx-apitoolkit-service/app
ENV JAVA_OPTS="-DlistPackagePrefix=app"
#ENV LOGGING_LEVEL_ROOT="info"
ENV LOGGING_LEVEL_ROOT="info"
ENV LOGGING_LEVEL_COM_NCR_TX="debug"
#ENV TX_INFINITY_DEVELOPMENT_START_SUSPENDED=true
#ADD echo /opt/tx-apitoolkit-service/app/echo
#ADD presence /opt/tx-apitoolkit-service/app/presence
#ADD message /opt/tx-apitoolkit-service/app/message
ADD chat /opt/tx-apitoolkit-service/app/chat
#ADD chat /opt/tx-apitoolkit-service/app/chat
COPY target/lib/tx-pubsub-kafka-*.jar $TX_EXTENSIONS_PATH
COPY target/lib/spring-kafka-*.jar $TX_EXTENSIONS_PATH
COPY target/lib/spring-tx-*.jar $TX_EXTENSIONS_PATH
COPY target/lib/spring-messaging-*.jar $TX_EXTENSIONS_PATH
COPY target/lib/kafka-clients-*.jar $TX_EXTENSIONS_PATH
COPY target/lib/spring-retry-*.jar $TX_EXTENSIONS_PATH
COPY target/lib/reactor-kafka-*.jar $TX_EXTENSIONS_PATH
#COPY target/lib/* $TX_EXTENSIONS_PATH
CMD ./start
