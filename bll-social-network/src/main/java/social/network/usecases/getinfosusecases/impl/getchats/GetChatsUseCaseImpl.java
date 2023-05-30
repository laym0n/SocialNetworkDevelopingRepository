package social.network.usecases.getinfosusecases.impl.getchats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.GetChatsDAService;
import social.network.dto.modelsdto.ChatDTO;
import social.network.dto.requests.GetChatsRequest;
import social.network.dto.requests.ReadChatRequest;
import social.network.dto.responses.GetChatsResponse;
import social.network.usecases.getinfosusecases.GetChatsUseCase;

@Service
@AllArgsConstructor
public class GetChatsUseCaseImpl implements GetChatsUseCase {
    private GetChatsDAService daService;
    @Override
    public GetChatsResponse getChatsForUser(GetChatsRequest request) {
        return new GetChatsResponse(
                daService.findAllDescriptionsForUser(request.getUserIdSenderRequest())
        );
    }

    @Override
    public ChatDTO readChat(ReadChatRequest request) {
        return new ChatDTO(
                daService.findAllMessageDTOForChat(request.getIdChat()),
                daService.findDescriptionForChat(request.getIdChat(), request.getIdUserSenderRequest()),
                daService.isFrozenDialogChat(request.getIdChat())
        );
    }
}
