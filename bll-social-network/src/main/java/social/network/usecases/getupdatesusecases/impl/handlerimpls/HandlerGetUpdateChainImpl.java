package social.network.usecases.getupdatesusecases.impl.handlerimpls;

import social.network.dto.requests.UpdateRequest;
import social.network.dto.responses.GetUpdateResponse;
import lombok.AllArgsConstructor;
import social.network.usecases.getupdatesusecases.impl.HandlerGetUpdate;

import java.util.Optional;

@AllArgsConstructor
public class HandlerGetUpdateChainImpl implements HandlerGetUpdate {
    private Optional<HandlerGetUpdate> nextHandler;
    private HandlerGetUpdate currentHandler;
    @Override
    public void getUpdateForUserById(UpdateRequest request, GetUpdateResponse response) {
        currentHandler.getUpdateForUserById(request, response);
        if(nextHandler.isPresent()){
            nextHandler.get().getUpdateForUserById(request, response);
        }
    }
}
