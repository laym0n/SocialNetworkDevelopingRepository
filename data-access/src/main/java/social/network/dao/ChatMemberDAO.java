package social.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import social.network.entities.ChatMemberEntity;

import java.util.List;

@Repository
public interface ChatMemberDAO extends JpaRepository<ChatMemberEntity, Integer> {
    long deleteByChatIdAndUserId(int chatId, int userId);
    @Query("select che.chatId from ChatMemberEntity che where che.userId = :userId")
    List<Integer> findChatIdsByUserId(@Param("userId") int userId);
}
