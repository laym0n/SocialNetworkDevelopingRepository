package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.EventEntity;
import social.network.jpa.jpadao.JPAEventDAO;

@Repository
@AllArgsConstructor
public class EventDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private JPAEventDAO jpaEventDAO;

    public void create(EventEntity eventEntity) {
        Integer maxId = (Integer) entityManager.createQuery("select max(e.id.id) from EventEntity e " +
                "where e.id.chatId = :chatId").setParameter("chatId", eventEntity.getPrimaryKey().getChatId())
                        .getSingleResult();
        eventEntity.getPrimaryKey().setId((maxId == null ? 0 : maxId + 1));
        jpaEventDAO.save(eventEntity);
    }
}
