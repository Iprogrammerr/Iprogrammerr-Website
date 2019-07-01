package com.iprogrammerr.website;

import com.iprogrammerr.website.model.Mapping;
import com.iprogrammerr.website.model.experience.Experiences;
import com.iprogrammerr.website.model.project.Projects;
import com.iprogrammerr.website.model.skill.Skills;
import com.iprogrammerr.website.respondent.AboutRespondent;
import com.iprogrammerr.website.respondent.ErrorRespondent;
import com.iprogrammerr.website.respondent.ExperienceRespondent;
import com.iprogrammerr.website.respondent.ProjectRespondent;
import com.iprogrammerr.website.respondent.SkillsRespondent;
import com.iprogrammerr.website.respondent.WelcomeRespondent;
import com.iprogrammerr.website.view.HtmlViews;
import com.iprogrammerr.website.view.Views;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
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
import java.util.concurrent.TimeUnit;

public class App {

    private static final String RESOURCES_CONTEXT = "resources";
    private static final String RESOURCES_NO_CACHE = "max-age=0";
    private static final String RESOURCES_CACHE = "max-age=" + TimeUnit.HOURS.toSeconds(1);

    public static void main(String... args) throws Exception {
        Configuration configuration = Configuration.fromCmd(args);

        File resources = configuration.getResources();

        File templates = new File(resources, "template");
        TemplateEngine engine = new TemplateEngine();
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix(templates.getPath() + File.separator);
        resolver.setCacheable(false);
        engine.setTemplateResolver(resolver);
        Views views = new HtmlViews(templates, engine);

        File database = configuration.getDatabase();
        Experiences experiences = new Experiences(new File(database, "experiences.json"));
        Projects projects = new Projects(new File(database, "projects.json"),
            RESOURCES_CONTEXT, new File(configuration.getPublicResources(), "projects"));
        Skills skills = new Skills(new File(database, "skills.json"));

        WelcomeRespondent welcomeRespondent = new WelcomeRespondent(views, experiences, projects, skills);
        ErrorRespondent errorRespondent = new ErrorRespondent(views);
        AboutRespondent aboutRespondent = new AboutRespondent(views);
        ExperienceRespondent experienceRespondent = new ExperienceRespondent(views, experiences);
        ProjectRespondent projectRespondent = new ProjectRespondent(views, projects);
        SkillsRespondent skillsRespondent = new SkillsRespondent(views, skills);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(welcomeRespondent, errorRespondent, aboutRespondent,
            experienceRespondent, projectRespondent, skillsRespondent);

        server(configuration, dispatcherServlet).start();
    }


    private static Server server(Configuration configuration, DispatcherServlet dispatcher) throws Exception {
        Server server = new Server();

        HandlerCollection handlers = new HandlerCollection();
        server.setHandler(handlers);

        handlers.addHandler(resourceHandler(RESOURCES_CONTEXT, configuration.getPublicResources(),
            configuration.shouldCacheStaticResources()));

        for (Mapping r : configuration.getMappings()) {
            handlers.addHandler(resourceHandler(r.context, new File(r.path), r.welcomeFile,
                configuration.shouldCacheStaticResources()));
        }

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(new ServletHolder(dispatcher), "/");
        handlers.addHandler(servletHandler);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(configuration.getPort());
        server.setConnectors(new Connector[]{connector});

        return server;
    }

    private static Handler resourceHandler(String context, File resource, String welcomeFile, boolean cache) {
        ResourceHandler handler = new ResourceHandler();
        handler.setBaseResource(Resource.newResource(resource));
        if (cache) {
            handler.setCacheControl(RESOURCES_CACHE);
        } else {
            handler.setCacheControl(RESOURCES_NO_CACHE);
        }
        if (!welcomeFile.isEmpty()) {
            handler.setWelcomeFiles(new String[]{welcomeFile});
        }
        ContextHandler contextHandler = new ContextHandler();
        contextHandler.setContextPath("/" + context);
        contextHandler.setHandler(handler);
        return contextHandler;
    }

    private static Handler resourceHandler(String context, File resource, boolean cache) {
        return resourceHandler(context, resource, "", cache);
    }
}
