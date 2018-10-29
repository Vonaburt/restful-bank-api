package ru.home.restfulbankapi;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    private static final String BASE_URI = "http://localhost:8080/myapp/";

    public static void main(String[] args) throws IOException {

        final ResourceConfig rc = new ResourceConfig().packages("ru.home.restfulbankapi");
        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        httpServer.start();
    }
}