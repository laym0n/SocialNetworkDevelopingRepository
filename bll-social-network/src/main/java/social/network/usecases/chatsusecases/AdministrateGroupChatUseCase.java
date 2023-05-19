package social.network.usecases.chatsusecases;

import social.network.dto.requests.BlockMessageRequest;
import social.network.dto.requests.ChangeChatMemberRolesRequest;
import social.network.dto.requests.KickOutChatMemberRequest;

public interface AdministrateGroupChatUseCase {
    void changeChatMemberRolesForMember(ChangeChatMemberRolesRequest request);

    void deleteMessage(BlockMessageRequest request);

    void kickOutChatMember(KickOutChatMemberRequest request);
}
