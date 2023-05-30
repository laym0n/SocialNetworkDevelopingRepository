package social.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import social.network.configs.BllConfig;
import social.network.configs.MvcConfig;
import social.network.configs.SecurityConfig;
//import socialnetwork.configurations.ApplicationConfig;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import socialnetwork.configurations.ApplicationConfig;
//import socialnetwork.dataaccess.jpa.TestClass;

import java.util.logging.Logger;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"social.network.jpa.jpadao"})
@EntityScan(basePackages = {"social.network.jpa.entities"})
@Import(value = {SecurityConfig.class, MvcConfig.class, BllConfig.class})
public class SocialNetworkApplication {
    private static Logger log = Logger.getLogger(SocialNetworkApplication.class.getName());

    public static void main(String[] args) {
        var ctx = SpringApplication.run(SocialNetworkApplication.class, args);
    }
}
