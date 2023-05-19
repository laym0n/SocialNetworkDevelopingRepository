package social.network.entities.user;

import lombok.Getter;

@Getter
public enum UserRole {
    SIMPLE_USER(0, 10),
    ADMIN(0, 50);
    int idGroup;
    int power;

    UserRole(int idGroup, int power) {
        this.idGroup = idGroup;
        this.power = power;
    }
}
