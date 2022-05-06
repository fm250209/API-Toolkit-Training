FROM ncr-csp-docker-snapshots.jfrog.io/tx-apitoolkit-service:2.4.0-latest
RUN mkdir /opt/tx-apitoolkit-service/app
ENV JAVA_OPTS="-DlistPackagePrefix=app"
ADD . /opt/tx-apitoolkit-service/app
CMD ./start
