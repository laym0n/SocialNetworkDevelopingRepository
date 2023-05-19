package social.network.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "private_chat", uniqueConstraints = @UniqueConstraint(columnNames = {"id_user", "name"}))
@Data
@PrimaryKeyJoinColumn(name = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrivateChatEntity extends ChatEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "id_user", nullable = false)
    @JoinColumn(table = "users", name = "id")
    private int userId;
}
