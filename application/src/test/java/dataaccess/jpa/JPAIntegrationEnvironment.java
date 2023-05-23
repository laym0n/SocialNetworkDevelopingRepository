package dataaccess.jpa;

import dataaccess.IntegrationEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import social.network.SocialNetworkApplication;

@SpringBootTest(classes = {SocialNetworkApplication.class})
public class JPAIntegrationEnvironment extends IntegrationEnvironment {
    @DynamicPropertySource
    static void jpaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.liquibase.enabled", () -> "false");
        registry.add("spring.datasource.url", singletonPostgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", singletonPostgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", singletonPostgreSQLContainer::getPassword);
        registry.add("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation",
                () -> Boolean.TRUE);
        registry.add("spring.jpa.properties.hibernate.dialect",
                () -> "org.hibernate.dialect.PostgreSQLDialect");
        registry.add("spring.jpa.show-sql",
                () -> "true");
    }
}
