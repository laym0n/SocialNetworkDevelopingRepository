package social.network.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Table(name = "chat_members")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMemberEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="user_id", nullable = false)
    private int userId;
    @Column(name ="chat_id", nullable = false)
    private int chatId;
    @Column(name ="is_blocked", nullable = false)
    private boolean isBlocked = false;
    @Column(name = "order_id_of_message_where_start_reading", nullable = false)
    private int orderIdOfMessageWhereStartReading;
    @Column(name = "last_order_id_of_checked_message", nullable = false)
    private int lastOrderIdOfCheckedMessage;
    @Column(name = "group_member_name")
    private String groupMemberName;
}
