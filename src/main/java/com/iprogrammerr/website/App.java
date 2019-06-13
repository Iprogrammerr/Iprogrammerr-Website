package com.iprogrammerr.website;

import com.iprogrammerr.website.respondent.WelcomeRespondent;
import com.iprogrammerr.website.view.HtmlViews;
import com.iprogrammerr.website.view.HtmlViewsTemplates;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.File;

public class App {

    public static void main(String... args) throws Exception {
        Configuration configuration = Configuration.fromCmd(args);

        String rootPath = configuration.getResourcesPath();
        File root = new File(rootPath.isEmpty() ? App.class.getResource("/").getPath() : rootPath);


        TemplateEngine engine = new TemplateEngine();
        AbstractConfigurableTemplateResolver resolver;
        if (rootPath.isEmpty()) {
            resolver = new ClassLoaderTemplateResolver();
            resolver.setCacheable(true);
            resolver.setPrefix("template" + File.separator);
        } else {
            resolver = new FileTemplateResolver();
            resolver.setCacheable(false);
            resolver.setPrefix(new File(root, "template").getPath() + File.separator);
        }
        engine.setTemplateResolver(resolver);
        HtmlViewsTemplates templates = new HtmlViewsTemplates(engine);

        WelcomeRespondent welcomeRespondent = new WelcomeRespondent(templates);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(welcomeRespondent);

        server(configuration, dispatcherServlet).start();
    }


    private static Server server(Configuration configuration, DispatcherServlet dispatcher) {
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
        resourceHandler.setCacheControl("max-age=0");
        ContextHandler resourceContext = new ContextHandler();
        resourceContext.setContextPath("/resources");
        resourceContext.setHandler(resourceHandler);
        handlers.addHandler(resourceContext);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(new ServletHolder(dispatcher), "/");
        handlers.addHandler(servletHandler);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(configuration.getPort());
        server.setConnectors(new Connector[]{connector});

        return server;
    }
}
