package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.BlackListUserEntity;
import social.network.jpa.entities.ids.BlackListUserEntityId;
import social.network.jpa.jpadao.JPAUsersBlackListDAO;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UsersBlackListDAO {
    private JPAUsersBlackListDAO jpaUsersBlackListDAO;
    @PersistenceContext
    private EntityManager entityManager;

    public boolean existsById(BlackListUserEntityId id) {
        return jpaUsersBlackListDAO.existsById(id);
    }

    public void create(BlackListUserEntity newEntity) {
        jpaUsersBlackListDAO.save(newEntity);
    }

    public void deleteById(BlackListUserEntityId id) {
        jpaUsersBlackListDAO.deleteById(id);
        BlackListUserEntity entity = entityManager.find(BlackListUserEntity.class, id);
        if (entity != null) {
            entityManager.detach(entity);
        }
    }

    public boolean existsAnyWithUsers(int firstUser, int secondUser) {
        return jpaUsersBlackListDAO.existsAnyWithUsers(firstUser, secondUser);
    }

    public Optional<BlackListUserEntity> findById(BlackListUserEntityId id) {
        return jpaUsersBlackListDAO.findById(id);
    }
}
