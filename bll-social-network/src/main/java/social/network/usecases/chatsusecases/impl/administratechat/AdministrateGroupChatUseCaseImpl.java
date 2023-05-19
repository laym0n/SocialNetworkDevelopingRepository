package social.network.usecases.chatsusecases.impl.administratechat;

import social.network.daservices.AdministrateChatDAService;
import social.network.dto.requests.BlockMessageRequest;
import social.network.dto.requests.ChangeChatMemberRolesRequest;
import social.network.dto.requests.KickOutChatMemberRequest;
import social.network.entities.administrateentities.Ban;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMemberBlockedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMemberChangedInfoEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMessageBlockedEvent;
import social.network.entities.chat.chatimpl.groupchat.ChatMemberRole;
import social.network.usecases.chatsusecases.AdministrateGroupChatUseCase;
import social.network.usecases.chatsusecases.impl.managegroupchats.strategies.CompareChatMembersByRolesStrategy;

import java.security.InvalidParameterException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;


public class AdministrateGroupChatUseCaseImpl implements AdministrateGroupChatUseCase {
    private AdministrateChatDAService daService;
    private CompareChatMembersByRolesStrategy compareChatMembersByRolesStrategy;

    @Override
    public void changeChatMemberRolesForMember(ChangeChatMemberRolesRequest request) {
        Map<Integer, ChatMemberRole> rolesOfOwnerRequest = daService
                .findRolesOfChatMember(
                        request.getIdUserSenderRequest(),
                        request.getChatId()
                );
        Map<Integer, ChatMemberRole> rolesOfMemberForChange = daService.findRolesOfChatMember(
                request.getIdChatMemberForChangeRoles(),
                request.getChatId()
        );

        int compareResult = compareChatMembersByRolesStrategy.compare(rolesOfOwnerRequest, rolesOfMemberForChange);
        if (compareResult != 1) {
            throw new InvalidParameterException("No right access for that operation");
        }

        Map<Integer, ChatMemberRole> newRoles = new HashMap<>();
        for (String newRole : request.getNewRoles()) {
            ChatMemberRole newChatMemberRole = ChatMemberRole.valueOf(newRole);
            newRoles.put(newChatMemberRole.getIdGroup(), newChatMemberRole);
        }
        ChatMemberChangedInfoEvent event = new ChatMemberChangedInfoEvent(request.getIdChatMemberForChangeRoles());
        daService.updateRolesForChatMemberAndSaveEvent(
                request.getIdChatMemberForChangeRoles(),
                request.getChatId(),
                newRoles,
                event
        );
    }

    @Override
    public void deleteMessage(BlockMessageRequest request) {
        Ban messageBan = request.getBanDTO().getBan();
        messageBan.setWhenBanned(OffsetDateTime.now());
        ChatMessageBlockedEvent event = new ChatMessageBlockedEvent(request.getIdChat(), request.getOrderIdMessage());
        daService.blockMessageAndSaveEvent(messageBan, request.getIdChat(), request.getOrderIdMessage(), event);
    }

    @Override
    public void kickOutChatMember(KickOutChatMemberRequest request) {
        Ban chatMemberBan = request.getBanDTO().getBan();
        chatMemberBan.setWhenBanned(OffsetDateTime.now());
        ChatMemberBlockedEvent event = new ChatMemberBlockedEvent(request.getIdChat(), request.getIdUser());
        daService.blockChatMemberAndSaveEvent(chatMemberBan, request.getIdChat(), request.getIdUser(), event);
    }
}
