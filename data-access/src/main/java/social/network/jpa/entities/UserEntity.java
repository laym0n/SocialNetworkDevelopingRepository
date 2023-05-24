package social.network.jpa.entities;

import social.network.entities.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
    private boolean isBlocked = false;
    @Column(name = "last_get_updates_time", nullable = false)
    private OffsetDateTime lastGetUpdatesTime = OffsetDateTime.now();
    @Column(name = "birthday")
    private LocalDate birthday;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRoleEntity> roles = new ArrayList<>();

    public UserEntity(User user, PersonalInfo personalInfo) {
        this.id = user.getId();
        this.isBlocked = user.isBlocked();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.firstName = personalInfo.getFirstName();
        this.secondName = personalInfo.getSecondName();
        this.avatar = null;
        this.birthday = personalInfo.getBirthday().orElse(null);
        this.isBlocked = user.isBlocked();
        this.lastGetUpdatesTime = user.getLastGetUpdatesTime();
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
    public UserProfile getUserProfile(){
        return new UserProfile(
                getPersonalInfo(),
                getUserInfo(),
                Optional.ofNullable(avatar),
                null
        );
    }
    public UserInfo getUserInfo(){
        return UserInfo.builder()
                .idUser(id)
                .userName(userName)
                .build();
    }
    public PersonalInfo getPersonalInfo(){
        return PersonalInfo
                .builder()
                .firstName(firstName)
                .secondName(secondName)
                .birthday(Optional.ofNullable(birthday))
                .build();
    }
    public List<UserRoleEntity> getRoles(){
        if (roles == null)
            roles = new ArrayList<>();
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return isBlocked() == that.isBlocked()
                && getId().equals(that.getId())
                && getUserName().equals(that.getUserName())
                && getPassword().equals(that.getPassword())
                && getFirstName().equals(that.getFirstName())
                && getSecondName().equals(that.getSecondName())
                && Arrays.equals(getAvatar(), that.getAvatar())
                && getLastGetUpdatesTime().truncatedTo(ChronoUnit.MINUTES)
                    .equals(that.getLastGetUpdatesTime().truncatedTo(ChronoUnit.MINUTES))
                && Objects.equals(getBirthday(), that.getBirthday())
                && getRoles().equals(that.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

