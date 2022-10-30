FROM openjdk:latest
WORKDIR /usr/src/app
RUN ls -ltr
COPY ./target/OnlineShop-*.jar OnlineShop.jar
CMD ["java","-jar","OnlineShop.jar"]
EXPOSE 8080
