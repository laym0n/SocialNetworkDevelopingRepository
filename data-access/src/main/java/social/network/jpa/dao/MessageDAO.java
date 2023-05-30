package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.MessageEntity;
import social.network.jpa.jpadao.JPAMessageDAO;

import java.util.List;

@Repository
@AllArgsConstructor
public class MessageDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private JPAMessageDAO jpaMessageDAO;

    public List<MessageEntity> findAllForChat(int idChat) {
        return entityManager.createQuery("select m from MessageEntity m where m.id.chatId = :id")
                .setParameter("id", idChat).getResultList();
    }
}
