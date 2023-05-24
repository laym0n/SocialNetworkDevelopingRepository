package social.network.jpa.jpadao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.EventTypeEntity;

import java.util.Optional;

@Repository
public interface JPAEventTypeDAO extends JpaRepository<EventTypeEntity, Integer> {
    Optional<EventTypeEntity> findByName(String name);
    @Query("select et.id from EventTypeEntity et where et.name = :name")
    int findIdByName(@Param("name") String name);
}
