package social.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import social.network.entities.ChatTypeEntity;

@Repository
public interface ChatTypeDAO extends JpaRepository<ChatTypeEntity, Integer> {
    @Query("select ct.id from ChatTypeEntity  ct where ct.name = :name")
    int findIdByName(@Param("name") String name);
}
