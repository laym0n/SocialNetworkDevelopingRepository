package social.network.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import social.network.usecases.usersusecases.impl.edituserprofile.handlerssendevent.SendEventsUserUpdateProfileFactory;
import social.network.usecases.usersusecases.impl.edituserprofile.handlerssendevent.SendEventsUserUpdateProfileStrategy;

@Configuration
public class BllConfig {
    @Bean
    public SendEventsUserUpdateProfileStrategy sendEventsUserUpdateProfileStrategy(
            SendEventsUserUpdateProfileFactory factory
    ) {
        return factory.getSendEventsUserUpdateProfileStrategy();
    }
}
