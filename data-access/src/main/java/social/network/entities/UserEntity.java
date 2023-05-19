package social.network.entities;

import social.network.entities.user.User;
import social.network.entities.user.UserInfo;
import jakarta.persistence.*;
import lombok.*;
import social.network.entities.user.UserRole;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Builder
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, name = "username")
    private String userName;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "second_name")
    private String secondName;

    @Column(name = "avatar")
    private byte[] avatar;

    @Column(nullable = false, name = "is_blocked")
    private boolean isBlocked;
    @Column(name = "last_get_updates_time", nullable = false)
    private OffsetDateTime lastGetUpdatesTime;
    @Column(name = "birthday")
    private OffsetDateTime birthday;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRoleEntity> roles = new ArrayList<>();

    public UserEntity(User user, UserInfo userInfo) {
        this.id = user.getId();
        this.isBlocked = user.isBlocked();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.firstName = userInfo.getFirstName();
        this.secondName = userInfo.getSecondName();
        this.avatar = userInfo.getAvatar();
        this.isBlocked = user.isBlocked();
        this.lastGetUpdatesTime = user.getLastGetUpdatesTime();
        roles = user.getRoles().values().stream()
                .map(userRole -> new UserRoleEntity(userRole))
                .toList();
    }
    public User getUser(){
        List<UserRole> userRoles =
                roles.stream()
                        .map(userRoleEntity->UserRole.valueOf(userRoleEntity.getName()))
                        .toList();
        User result = User.builder()
                .id(id)
                .userName(userName)
                .password(password)
                .lastGetUpdatesTime(lastGetUpdatesTime)
                .isBlocked(isBlocked)
                .build();
        result.setRolesViaList(userRoles);
        return result;
    }
}

