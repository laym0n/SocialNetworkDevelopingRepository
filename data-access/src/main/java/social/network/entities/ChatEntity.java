package social.network.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Table(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked = false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_type_id")
    private ChatTypeEntity type;
}
