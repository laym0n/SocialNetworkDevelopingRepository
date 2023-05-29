package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.ChatTypeEntity;
import social.network.jpa.jpadao.JPAChatTypeDAO;

@Repository
@AllArgsConstructor
public class ChatTypeDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private JPAChatTypeDAO jpaChatTypeDAO;
    public Integer findIdByName(String name) {
        return jpaChatTypeDAO.findIdByName(name);
    }

    public ChatTypeEntity findByName(String dialogChatTypeColumnValue) {
        return jpaChatTypeDAO.findByName(dialogChatTypeColumnValue);
    }
}
