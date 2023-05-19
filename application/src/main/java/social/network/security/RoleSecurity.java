package social.network.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleSecurity implements GrantedAuthority {
    private String name;
    @Override
    public String getAuthority() {
        return name;
    }
}
