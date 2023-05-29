package social.network.jpa.jpadao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.entities.ChatEntity;

@Repository
public interface JPAChatDAO extends JpaRepository<ChatEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update ChatEntity c set c.name = ?1, c.description = ?2 where c.id = ?3")
    int updateNameAndDescriptionById(String name, String description, int id);
    @Transactional
    @Modifying
    @Query("update ChatEntity c set c.avatar = ?1 where c.id = ?2")
    int updateAvatarById(byte[] avatar, int id);
    @Transactional
    @Modifying
    @Query("update ChatEntity c set c.isActive = ?1 where c.id = ?2")
    int updateIsActiveById(boolean isActive, int id);

}
