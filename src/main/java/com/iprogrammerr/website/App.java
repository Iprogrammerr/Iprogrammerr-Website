package com.iprogrammerr.website;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.resource.Resource;

import java.io.File;

public class App {

    public static void main(String... args) throws Exception {
        Configuration configuration = Configuration.fromCmd(args);

        Server server = new Server();

        HandlerCollection handlers = new HandlerCollection();
        server.setHandler(handlers);

        ResourceHandler resourceHandler = new ResourceHandler();
        String rootPath = configuration.getResourcesPath();
        if (rootPath.isEmpty()) {
            resourceHandler.setBaseResource(Resource.newClassPathResource("public"));
        } else {
            resourceHandler.setBaseResource(Resource.newResource(new File(rootPath, "public")));
        }
        ContextHandler resourceContext = new ContextHandler();
        resourceContext.setContextPath("/resources");
        resourceContext.setHandler(resourceHandler);
        handlers.addHandler(resourceContext);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(DispatcherServlet.class, "/");
        handlers.addHandler(servletHandler);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(configuration.getPort());
        server.setConnectors(new Connector[]{connector});

        server.start();
    }
}
