package com.iprogrammerr.website;

import com.iprogrammerr.website.model.Experiences;
import com.iprogrammerr.website.model.Projects;
import com.iprogrammerr.website.model.Skills;
import com.iprogrammerr.website.respondent.AboutRespondent;
import com.iprogrammerr.website.respondent.ExperienceRespondent;
import com.iprogrammerr.website.respondent.ProjectRespondent;
import com.iprogrammerr.website.respondent.SkillRespondent;
import com.iprogrammerr.website.respondent.WelcomeRespondent;
import com.iprogrammerr.website.view.HtmlViews;
import com.iprogrammerr.website.view.Views;
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
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.File;

public class App {

    public static void main(String... args) throws Exception {
        Configuration configuration = Configuration.fromCmd(args);

        File resources = new File(configuration.getResourcesPath());

        File templates = new File(resources, "template");
        TemplateEngine engine = new TemplateEngine();
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix(templates.getPath() + File.separator);
        resolver.setCacheable(false);
        engine.setTemplateResolver(resolver);
        Views views = new HtmlViews(templates, engine);

        String databasePath = resources.getPath() + File.separator + "database";
        Experiences experiences = new Experiences(databasePath + File.separator + "experiences.json");
        Projects projects = new Projects(databasePath + File.separator + "projects.json");
        Skills skills = new Skills(databasePath + File.separator + "skills.json");

        WelcomeRespondent welcomeRespondent = new WelcomeRespondent(views, experiences, projects, skills);
        AboutRespondent aboutRespondent = new AboutRespondent(views);
        ExperienceRespondent experienceRespondent = new ExperienceRespondent(views, experiences);
        ProjectRespondent projectRespondent = new ProjectRespondent(views, projects);
        SkillRespondent skillRespondent = new SkillRespondent(views, skills);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(welcomeRespondent, aboutRespondent,
            experienceRespondent, projectRespondent, skillRespondent);

        server(configuration, dispatcherServlet).start();
    }


    private static Server server(Configuration configuration, DispatcherServlet dispatcher) {
        Server server = new Server();

        HandlerCollection handlers = new HandlerCollection();
        server.setHandler(handlers);

        ResourceHandler resourceHandler = new ResourceHandler();
        String rootPath = configuration.getResourcesPath();
        resourceHandler.setBaseResource(Resource.newResource(new File(rootPath, "public")));
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
