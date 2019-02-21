FROM maven
ENTRYPOINT ["./test.sh"]
COPY pom.xml .
RUN mvn verify --fail-never
COPY . .
RUN chmod +x test.sh
