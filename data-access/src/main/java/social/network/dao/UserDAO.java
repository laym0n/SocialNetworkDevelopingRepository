package social.network.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import social.network.entities.UserEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends CrudRepository<UserEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update UserEntity u set u.firstName = ?1, u.secondName = ?2, u.avatar = ?3, u.birthday = ?4 where u.id = ?5")
    int updateProfileFields(@NonNull String firstName, @NonNull String secondName, @Nullable byte[] avatar, @Nullable OffsetDateTime birthday, @NonNull Integer id);

    Optional<UserEntity> findByUserName(String userName);
    @Query("SELECT u.password FROM UserEntity u WHERE u.userName = :userName")
    String findPasswordByUserName(@Param("userName") String userName);
}
