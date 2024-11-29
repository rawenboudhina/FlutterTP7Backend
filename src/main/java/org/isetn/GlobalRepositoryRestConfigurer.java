package org.isetn;

import org.isetn.entities.Classe;
import org.isetn.entities.Etudiant;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class GlobalRepositoryRestConfigurer implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration, CorsRegistry cors) {
        repositoryRestConfiguration.setReturnBodyOnCreate(true);
        repositoryRestConfiguration.setReturnBodyOnUpdate(true);

        // Expose IDs for these entities
        repositoryRestConfiguration.exposeIdsFor(Etudiant.class, Classe.class);

        cors.addMapping("/**")
        .allowedOrigins("http://localhost:4200", "http://172.20.10.2:8081") 
        .allowedHeaders("*")
        .allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");

    }
}
