# Se_eX
## DOCKER ISTRUZIONI
### 1) START DOCKER
    a) Create a docker directory
    b) For each dockerized project create a folder

### 2) EXAMPLE WITH SINGLE SERVICE CONTAINER
    a) SOAP-WS should contain:
        - DockerFile
        - Server.jar
        - docker-compose.yml
    b) DockerFile:
    // container that offer java jdk
        FROM openjdk:8-alpine 
    // add curl to test service
        RUN apk --no-cache add curl
    // copy the service on the container
        COPY ./SOAP-WebService-1.0.jar /usr/app/
    // specify workidir
        WORKDIR /usr/app
    // expose docker on port (where service expose same of the code)
        EXPOSE 7777
    // command to launch java ws
        ENTRYPOINT ["java", "-cp", "SOAP-WebService-1.0.jar", "it.sapienza.softeng.soapws.Server"]
    c) Build the container
        $docker build -t soap-docker . --file ./DockerFile
    d) Map the container through docker-compose
        version: '3'
        //building only 1 service
        services:
        //name the service as "soap"
        soap:
            //build (the same as point c)
            build: .
            //Port forwarding => container expose 7777 and "world" access with 9999
            //                   so you can access to /addrs:9999/
            ports:
            - "9999:7777"
    e) Run docker compose
        $docker-compose up --build

### 3) EDIT PROJECT TO INTERFACE WITH DOCKER (HOST + VM)
    a) On server side replace localhost with 0.0.0.0:7777
        (N.B. 
        7777 should be the port offered by the container!
        0.0.0.0 will be replaced by the docker engine)
