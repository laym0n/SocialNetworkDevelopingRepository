package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.jpadao.JPAEventTypeDAO;

@Repository
@AllArgsConstructor
public class EventTypeDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private JPAEventTypeDAO jpaEventTypeDAO;

    public Integer findIdByName(String name) {
        return jpaEventTypeDAO.findIdByName(name);
    }
}
