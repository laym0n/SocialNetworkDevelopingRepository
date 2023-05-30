package social.network.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import social.network.usecases.usersusecases.ManageBlackListUseCase;
import social.network.usecases.usersusecases.impl.manageblacklist.FactoryManageBlackList;

@Configuration
public class BllConfig {
    @Bean
    public ManageBlackListUseCase manageBlackListUseCase(FactoryManageBlackList factory) {
        return factory.getManageBlackListUseCase();
    }
}
