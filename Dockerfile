FROM maven
ENTRYPOINT ["./test.sh"]
COPY . .
RUN chmod +x test.sh
