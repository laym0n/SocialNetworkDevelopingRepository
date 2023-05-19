package social.network.usecases.chatsusecases.impl.readchat;

import social.network.daservices.ReadChatDAService;
import social.network.dto.modelsdto.ChatDTO;
import social.network.dto.requests.chat.ReadAllChatRequest;
import social.network.entities.chat.chatimpl.groupchat.GroupChat;
import social.network.usecases.chatsusecases.ReadChatUseCase;

public class ReadChatUseCaseImpl implements ReadChatUseCase {
    private ReadChatDAService daService;

    @Override
    public ChatDTO readFullChat(ReadAllChatRequest request) {
        GroupChat groupChat = daService.loadChatWithAllMessages(request.getIdChat());
        return new ChatDTO(groupChat);
    }
}
