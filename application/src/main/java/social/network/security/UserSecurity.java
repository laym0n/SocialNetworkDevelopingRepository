package social.network.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import social.network.entities.user.User;

import java.util.Collection;

@Getter
@Setter
public class UserSecurity extends org.springframework.security.core.userdetails.User {
    private int userId;

    public UserSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(0, username, password, authorities);
    }

    public UserSecurity(int id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = id;
    }

    public UserSecurity(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        this(0, username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserSecurity(int id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = id;
    }

    public UserSecurity(User foundedUser) {
        this(foundedUser.getId(),
                foundedUser.getUserName(),
                foundedUser.getPassword(),
                !foundedUser.isBlocked(),
                true,
                true,
                true,
                foundedUser.getRoles().values()
                        .stream().map(i -> new RoleSecurity(i.name()))
                        .toList()
        );
    }
}
