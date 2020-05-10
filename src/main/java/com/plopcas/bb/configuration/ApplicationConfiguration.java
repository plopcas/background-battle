package com.plopcas.bb.configuration;

import com.plopcas.bb.model.UserSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Configuration
@EnableScheduling
public class ApplicationConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
    public UserSession userSession() {
        return new UserSession();
    }
}
