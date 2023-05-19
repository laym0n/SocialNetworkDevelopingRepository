package social.network.usecases.chatsusecases;

import social.network.dto.modelsdto.ChatDTO;
import social.network.dto.requests.chat.ReadAllChatRequest;

public interface ReadChatUseCase {
    ChatDTO readFullChat(ReadAllChatRequest request);
}
