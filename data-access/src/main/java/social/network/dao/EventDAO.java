package social.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.network.entities.EventEntity;

@Repository
public interface EventDAO extends JpaRepository<EventEntity, Integer> {
}
