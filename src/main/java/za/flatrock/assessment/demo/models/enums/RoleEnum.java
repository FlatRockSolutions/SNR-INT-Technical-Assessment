package za.flatrock.assessment.demo.models.enums;

import java.util.Arrays;
import java.util.Optional;

public enum RoleEnum {

    ADMIN,
    USER,
    STAFF;

    public static RoleEnum getRole(String roleDescription) {
        return RoleEnum.valueOf(roleDescription.toUpperCase());
    }
    public static boolean isValidRole(String roleDescription) {
        Optional<RoleEnum> roleEnum = Arrays.stream(RoleEnum.values()).filter(role -> role.name().equalsIgnoreCase(roleDescription)).findFirst();
        return roleEnum.isPresent();
    }
}
