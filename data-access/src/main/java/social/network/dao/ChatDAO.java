package social.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.network.entities.ChatEntity;

@Repository
public interface ChatDAO extends JpaRepository<ChatEntity, Integer> {

}
