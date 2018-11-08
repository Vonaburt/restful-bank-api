package ru.home.restfulbankapi;

import lombok.Getter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Server {

    @Getter
    private static final String BASE_URI = "http://localhost:8080/myapp/";

    private static HttpServer httpServer;

    public static synchronized void runServer(String baseUri) throws IOException {

        if (httpServer != null) {
            return;
        }

        final ResourceConfig rc = new ResourceConfig().packages("ru.home.restfulbankapi");
        httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), rc);
        httpServer.start();
    }

    public static synchronized void stopServer() {
        if (httpServer.isStarted()) {
            httpServer.shutdown();
        }
    }

    public static void main(String[] args) throws IOException {
        runServer(BASE_URI);
    }
}