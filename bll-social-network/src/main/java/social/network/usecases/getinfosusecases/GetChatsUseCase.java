package social.network.usecases.getinfosusecases;

import social.network.dto.modelsdto.ChatDTO;
import social.network.dto.requests.GetChatsRequest;
import social.network.dto.requests.ReadChatRequest;
import social.network.dto.responses.GetChatsResponse;

public interface GetChatsUseCase {
    GetChatsResponse getChatsForUser(GetChatsRequest request);
    ChatDTO readChat(ReadChatRequest request);
}
