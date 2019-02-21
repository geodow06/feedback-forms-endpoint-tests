FROM maven
ENTRYPOINT ["./test.sh"]
COPY . .
