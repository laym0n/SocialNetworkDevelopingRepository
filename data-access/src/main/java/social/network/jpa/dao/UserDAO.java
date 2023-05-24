package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.jpadao.JPAUserDAO;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDAO {
    private JPAUserDAO jpaUserDAO;
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<UserEntity> findByUserName(String username) {
        return jpaUserDAO.findByUserName(username);
    }

    public UserEntity create(UserEntity newUserEntity) {
        return jpaUserDAO.save(newUserEntity);
    }

    public Optional<UserEntity> findById(Integer id) {
        return jpaUserDAO.findById(id);
    }

    public int updateAvatar(byte[] newAvatar, int userId) {
        int count = jpaUserDAO.updateAvatar(newAvatar, userId);
        detachEntity(userId);
        return count;
    }

    public int updateProfileFields(String firstName, String secondName, LocalDate newBirthday, int idUser) {
        int count = jpaUserDAO.updateProfileFields(
                firstName,
                secondName,
                newBirthday,
                idUser
        );
        detachEntity(idUser);
        return count;
    }
    private void detachEntity(int id) {
        UserEntity entity = entityManager.find(UserEntity.class, id);
        entityManager.detach(entity);
    }
}
