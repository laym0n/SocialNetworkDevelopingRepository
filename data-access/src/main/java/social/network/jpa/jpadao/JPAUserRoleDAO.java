package social.network.jpa.jpadao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.UserRoleEntity;

@Repository
public interface JPAUserRoleDAO extends JpaRepository<UserRoleEntity, Integer> {
    UserRoleEntity findByName(String name);
}
