package social.network.jpa.jpadao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.MessageEntity;
import social.network.jpa.entities.ids.MessageEntityId;

@Repository
public interface JPAMessageDAO extends CrudRepository<MessageEntity, MessageEntityId> {
}
