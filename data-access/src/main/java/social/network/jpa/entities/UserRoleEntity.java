package social.network.jpa.entities;

import lombok.AllArgsConstructor;
import social.network.entities.user.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;

    public UserRoleEntity(UserRole userRole) {
        this.id = userRole.ordinal();
        this.name = userRole.name();
    }
}
