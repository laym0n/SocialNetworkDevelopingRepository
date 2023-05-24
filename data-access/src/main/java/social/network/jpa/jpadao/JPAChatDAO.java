package social.network.jpa.jpadao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.ChatEntity;

@Repository
public interface JPAChatDAO extends JpaRepository<ChatEntity, Integer> {

}
