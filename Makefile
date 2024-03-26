prepare:
	docker-compose up -d
	bash wait.sh
run:
	cd shared-parking-spot-app && \
	mvn clean install -DskipDocumentation=true -DskipTests && \
	docker build -t shared-parking-spot . && \
	docker run --rm -p 7777:7777 --network=shared-parking-spot_system --name app shared-parking-spot
clean:
	docker ps -a | grep app && docker stop app && docker rm app || true
	cd shared-parking-spot-app/ && \
	docker-compose down && \
    mvn clean
sql:
	cd shared-parking-spot-app && \
	mvn clean install -DskipTests -DgenerateEntriesFromDB=true && \
	rm src/main/java/com/parkingsharing/sql/FlywaySchemaHistory.java && \
	sed -i '' '2d' src/main/java/com/parkingsharing/sql/Company.java
swagger:
	cd shared-parking-spot-app && \
	mvn clean install -DskipDocumentation=false -DskipTests
migrate:
	cd shared-parking-spot-app && \
    mvn spring-boot:run -Dconsole=true -Dspring-boot.run.profiles=console-application
