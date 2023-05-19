package social.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.network.entities.UserRoleEntity;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRoleEntity, Integer> {
    UserRoleEntity findByName(String name);
}
