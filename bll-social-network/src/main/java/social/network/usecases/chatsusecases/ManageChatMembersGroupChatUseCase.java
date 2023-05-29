package social.network.usecases.chatsusecases;

import social.network.dto.requests.chat.AddChatMemberRequest;
import social.network.dto.requests.chat.EditChatMemberProfileRequest;
import social.network.dto.requests.chat.LeaveFromChatRequest;

public interface ManageChatMembersGroupChatUseCase {

    void addChatMember(AddChatMemberRequest request);

    void leaveFromChat(LeaveFromChatRequest request);
    void editChatMember(EditChatMemberProfileRequest request);
}
