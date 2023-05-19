package social.network.usecases.chatsusecases;

//import dto.requests.chat.*;
import social.network.dto.requests.chat.*;

public interface ManageGroupChatsUseCase {
    void createChat(CreateGroupChatRequest request);

    void addChatMember(AddChatMemberRequest request);

    void leaveFromChat(LeaveFromChatRequest request);

    void editChatMember(EditChatMemberProfileRequest request);

    void editChatInfo(EditChatRequest request);
}
