run:
	cd shared-parking-spot-app/ && \
    mvn install -DskipTests=true && \
    docker-compose up
clean:
	cd shared-parking-spot-app/ && \
	docker-compose down && \
    mvn clean