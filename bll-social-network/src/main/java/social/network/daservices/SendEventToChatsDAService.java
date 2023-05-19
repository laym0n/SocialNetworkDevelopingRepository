package social.network.daservices;

import social.network.entities.chat.changeshistory.chatchangeevents.ChatMemberChangedInfoEvent;

public interface SendEventToChatsDAService {
    void addEventToAllChatsWithUserByUserId(ChatMemberChangedInfoEvent event, int idUser);
}
