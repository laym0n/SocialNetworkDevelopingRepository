package social.network.impl.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import social.network.entities.UserEntity;

@Repository
public interface UserDAO extends CrudRepository<UserEntity, Integer> {
}
