package social.network.jpa.jpadao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.EventEntity;

@Repository
public interface JPAEventDAO extends JpaRepository<EventEntity, Integer> {
}
