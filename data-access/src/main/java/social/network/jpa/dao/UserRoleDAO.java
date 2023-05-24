package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.UserRoleEntity;
import social.network.jpa.jpadao.JPAUserRoleDAO;

@Repository
@AllArgsConstructor
public class UserRoleDAO {
    private JPAUserRoleDAO jpaUserRoleDAO;
    @PersistenceContext
    private EntityManager entityManager;

    public UserRoleEntity findByName(String name) {
        return jpaUserRoleDAO.findByName(name);
    }
}
