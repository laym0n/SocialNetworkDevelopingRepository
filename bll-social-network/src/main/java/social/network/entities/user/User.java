package social.network.entities.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
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
    private Map<Integer, UserRole> roles;
    protected boolean isBlocked = false;
    private OffsetDateTime lastGetUpdatesTime = OffsetDateTime.now();

    public User(String userName, String password, Map<Integer, UserRole> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }
    public void setRolesViaList(List<UserRole> newRoles){
        roles = newRoles.stream()
                .collect(Collectors.toMap(i->i.getIdGroup(), i->i));
    }
}
