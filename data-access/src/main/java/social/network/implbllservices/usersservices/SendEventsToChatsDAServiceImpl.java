package social.network.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.dao.ChatMemberDAO;
import social.network.dao.EventDAO;
import social.network.dao.EventTypeDAO;
import social.network.daservices.SendEventToChatsDAService;
import social.network.entities.EventEntity;
import social.network.entities.EventTypeEntity;
import social.network.entities.PrimaryKeyEventEntity;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMemberChangedInfoEvent;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SendEventsToChatsDAServiceImpl implements SendEventToChatsDAService {
    private EventDAO eventDAO;
    private EventTypeDAO eventTypeDAO;
    private ChatMemberDAO chatMemberDAO;

    @Override
    public void addEventToAllChatsWithUserByUserId(ChatMemberChangedInfoEvent event, int idUser) {
        List<Integer> chatIdsWithUser = chatMemberDAO.findChatIdsByUserId(idUser);
        int idTypeEvent = eventTypeDAO.findIdByName("CHAT_MEMBER_CHANGED_INFO");
        List<EventEntity> savedEvents = new ArrayList<>();
        for (Integer idChat : chatIdsWithUser) {
            EventEntity newEvent = new EventEntity(new PrimaryKeyEventEntity(0, idChat), null, idUser,
                    new EventTypeEntity(idTypeEvent, "CHAT_MEMBER_CHANGED_INFO"));
            savedEvents.add(newEvent);
        }
        eventDAO.saveAll(savedEvents);
    }
}
