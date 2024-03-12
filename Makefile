run:
	cd shared-parking-spot-app/ && \
    mvn clean install && \
    cd .. && \
    docker build -t shared-parking-spot . && \
    docker run -d -p 7777:7777 shared-parking-spot
clean:
	cd shared-parking-spot-app/ && \
    mvn clean