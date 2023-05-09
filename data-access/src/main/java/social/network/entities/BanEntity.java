package social.network.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bans")
@Data
public class BanEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    @JoinColumn(table = "users", referencedColumnName = "user_id", name = "id")
    private Integer userIdBlocked;
    @Column(name = "user_blocker_id")
    @JoinColumn(table = "users", referencedColumnName = "user_id", name = "id", nullable = false)
    private int idBlocker;

    @Column(nullable = false, name = "description")
    private String description;

    @Column(nullable = false, name = "when_banned")
    private LocalDateTime whenBanned;
}
