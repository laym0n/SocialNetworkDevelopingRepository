package social.network.jpa.entities;

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
    private Integer id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private UserEntity blockedUser;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_blocker_id", nullable = false)
    private UserEntity blocker;

    @Column(nullable = false, name = "description")
    private String description;

    @Column(nullable = false, name = "when_banned")
    private LocalDateTime whenBanned;
}
