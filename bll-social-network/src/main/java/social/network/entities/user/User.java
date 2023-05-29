package social.network.entities.user;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private int id;
    private String userName;
    private String password;
    protected boolean isBlocked;
    @EqualsAndHashCode.Exclude
    private OffsetDateTime lastGetUpdatesTime = OffsetDateTime.now();
    private Map<Integer, UserRole> roles;

    public User(String userName, String password, Map<Integer, UserRole> roles) {
        this(0, userName, password, false, OffsetDateTime.now(), roles);
    }

    public User(String userName, String password) {
        this(userName, password, null);
        this.roles = getEmptyMap();
    }

    public void setRolesViaList(List<UserRole> newRoles) {
        roles = newRoles.stream()
                .collect(Collectors.toMap(UserRole::getIdGroup, i -> i));
    }

    public Map<Integer, UserRole> getRoles() {
        if (roles == null) {
            roles = getEmptyMap();
        }
        return roles;
    }

    private Map<Integer, UserRole> getEmptyMap() {
        return new HashMap<>();
    }
}
