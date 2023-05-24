package social.network.jpa.jpadao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.entities.PrivateChatEntity;

import java.util.Optional;

@Repository
public interface JPAPrivateChatDAO extends JpaRepository<PrivateChatEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update PrivateChatEntity p set p.name = ?1 where p.id = ?2 and p.userId = ?3")
    int updateNameByIdAndUserId(String name, int id, int userId);
    @Transactional
    @Modifying
    @Query("update PrivateChatEntity p set p.name = ?1 where p.name = ?2 and p.userId = ?3")
    int updateNameByNameAndUserId(String newName, String name, int userId);
    long deleteByIdAndUserId(int id, int userId);
    long deleteByNameAndUserId(String name, int userId);
    Optional<PrivateChatEntity> findByUserIdAndName(int userId, String name);
}
