package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.jpa.jpadao.JPAChatMemberDAO;
import social.network.jpa.jpadao.JPAEventDAO;
import social.network.jpa.jpadao.JPAEventTypeDAO;
import social.network.daservices.SendEventToChatsDAService;
import social.network.jpa.entities.EventEntity;
import social.network.jpa.entities.EventTypeEntity;
import social.network.jpa.entities.PrimaryKeyEventEntity;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMemberChangedInfoEvent;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SendEventsToChatsDAServiceImpl implements SendEventToChatsDAService {
    private JPAEventDAO JPAEventDAO;
    private JPAEventTypeDAO JPAEventTypeDAO;
    private JPAChatMemberDAO JPAChatMemberDAO;

    @Override
    public void addEventToAllChatsWithUserByUserId(ChatMemberChangedInfoEvent event, int idUser) {
        List<Integer> chatIdsWithUser = JPAChatMemberDAO.findChatIdsByUserId(idUser);
        int idTypeEvent = JPAEventTypeDAO.findIdByName("CHAT_MEMBER_CHANGED_INFO");
        List<EventEntity> savedEvents = new ArrayList<>();
        for (Integer idChat : chatIdsWithUser) {
            EventEntity newEvent = new EventEntity(new PrimaryKeyEventEntity(0, idChat), null, idUser,
                    new EventTypeEntity(idTypeEvent, "CHAT_MEMBER_CHANGED_INFO"));
            savedEvents.add(newEvent);
        }
        JPAEventDAO.saveAll(savedEvents);
    }
}
