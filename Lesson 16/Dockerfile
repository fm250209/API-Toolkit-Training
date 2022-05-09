FROM ncr-csp-docker-snapshots.jfrog.io/tx-apitoolkit-service:2.4.0-latest
RUN mkdir /opt/tx-apitoolkit-service/app
ENV JAVA_OPTS="-DlistPackagePrefix=app"
COPY target/tx-training-actions-1.0.0-SNAPSHOT.jar $TX_EXTENSIONS_PATH
ADD . /opt/tx-apitoolkit-service/app
CMD ./start
