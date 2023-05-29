package social.network.usecases.chatsusecases.impl.managegroupchats;

import social.network.daservices.ManageChatsDAService;
import social.network.entities.chat.chatimpl.groupchat.GroupChat;
import lombok.AllArgsConstructor;
import social.network.dto.requests.chat.*;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.usecases.chatsusecases.ManageGroupChatsUseCase;
import social.network.usecases.chatsusecases.impl.managegroupchats.strategies.CanEditChatMemberProfileStrategy;
import social.network.usecases.chatsusecases.impl.managegroupchats.strategies.ReplaceOwnerStrategy;

@AllArgsConstructor
public class ManageGroupChatsUseCaseImpl implements ManageGroupChatsUseCase {
    private ManageChatsDAService daService;

    @Override
    public void createChat(CreateGroupChatRequest request) {
        GroupChat newGroupChat = new GroupChat(request.getChatProfile());
        ChatCreatedEvent event = new ChatCreatedEvent();
        daService.createChatWithCreatorAndSimpleMembersAndSaveEvent(
                newGroupChat,
                request.getIdUserSenderRequest(),
                request.getSimpleUsersIds(),
                event
        );
    }

//    @Override
//    public void addChatMember(AddChatMemberRequest request) {
//        daService.addSimpleMemberByUserIdAndSaveEvent(request.getIdNewMember(), request.getIdChat());
//    }
//
//    @Override
//    public void leaveFromChat(LeaveFromChatRequest request) {
//        Map<Integer, ChatMemberRole> rolesOfRemoved = daService.setNotActiveChatMemberAndAddEvent(request.getIdMemberOwnerRequest());
//        ChatMemberRole owner = ChatMemberRole.OWNER;
//        if (rolesOfRemoved.get(owner.getIdGroup()).equals(owner)) {
//            replaceOwnerStrategy.replaceOwner(request.getIdChat());
//        }
//    }
//
//    @Override
//    public void editChatMember(EditChatMemberProfileRequest request) {
//        canEditChatMemberProfileStrategy.checkIfCanEdit(request);
//
//        ChatMemberChangedInfoEvent event = new ChatMemberChangedInfoEvent(request.getIdChatMemberForEdit());
//        daService.updateChatMemberInfoAndAddEvent(
//                request.getGroupChatMemberInfoDTO().getGroupChatMemberInfo(),
//                request.getIdChatMemberForEdit(),
//                request.getIdChat(),
//                event
//        );
//    }
//
//    @Override
//    public void editChatInfo(EditChatRequest request) {
//        ChatInfoChangedEvent event = new ChatInfoChangedEvent();
//        daService.updateGroupChatInfoAndSaveEvent(request.getNewGroupChatInfoDTO().getChatInfo(), request.getIdChat(), event);
//    }
}
