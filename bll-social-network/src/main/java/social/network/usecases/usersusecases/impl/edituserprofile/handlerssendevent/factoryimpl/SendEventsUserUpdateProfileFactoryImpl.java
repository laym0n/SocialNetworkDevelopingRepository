package social.network.usecases.usersusecases.impl.edituserprofile.handlerssendevent.factoryimpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import social.network.daservices.SendEventToChatsDAService;
import social.network.usecases.usersusecases.impl.edituserprofile.handlerssendevent.SendEventsToChatsImplStrategy;
import social.network.usecases.usersusecases.impl.edituserprofile.handlerssendevent.SendEventsUserUpdateProfileFactory;
import social.network.usecases.usersusecases.impl.edituserprofile.handlerssendevent.SendEventsUserUpdateProfileStrategy;

@Component
@AllArgsConstructor
public class SendEventsUserUpdateProfileFactoryImpl implements SendEventsUserUpdateProfileFactory {
    private SendEventToChatsDAService daService;
    @Override
    public SendEventsUserUpdateProfileStrategy getSendEventsUserUpdateProfileStrategy() {
        SendEventsToChatsImplStrategy strategy = new SendEventsToChatsImplStrategy(daService);
        return strategy;
    }
}
