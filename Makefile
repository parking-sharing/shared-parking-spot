run:
	cd shared-parking-spot-app/ && \
    mvn clean install && \
    docker build -t shared-parking-spot . && \
    docker run -p 7777:7777 shared-parking-spot
clean:
	cd shared-parking-spot-app/ && \
    mvn clean