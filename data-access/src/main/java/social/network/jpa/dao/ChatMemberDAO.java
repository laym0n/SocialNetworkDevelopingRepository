package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.ChatMemberEntity;
import social.network.jpa.jpadao.JPAChatMemberDAO;

@Repository
@AllArgsConstructor
public class ChatMemberDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private JPAChatMemberDAO chatMemberDAO;
    public void create(ChatMemberEntity newChatMemberEntity) {
        chatMemberDAO.save(newChatMemberEntity);
    }

    public Integer findIdUserChatMemberOfChat(int idPrivateChat) {
        return (Integer) entityManager.createQuery("select cm.userId from ChatMemberEntity cm " +
                "where cm.chatId = :chatId").setParameter("chatId", idPrivateChat).getSingleResult();
    }
}