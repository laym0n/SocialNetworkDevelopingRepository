package social.network.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.chat.chatimpl.privatechat.PrivateChat;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Builder
@Table(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "rules")
    private String rules;
    @Column(name = "avatar")
    private byte[] avatar;
    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked = false;
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
    @Column(name = "is_frozen_due_to_black_list", nullable = false)
    private boolean isFrozenDueToBlacklist = false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_type_id")
    private ChatTypeEntity type;

    public ChatEntity(PrivateChat privateChat) {
        this(
                privateChat.getId(),
                privateChat.getProfile().getDescription().getName(),
                privateChat.getProfile().getDescription().getDescription().orElse(null),
                null,
                privateChat.getProfile().getAvatar().orElse(null),
                privateChat.isBlocked(),
                privateChat.isActive(),
                false,
                null
        );
    }
}
