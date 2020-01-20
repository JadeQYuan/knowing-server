package plus.knowing.constant;

public enum RoleEnum {
    Admin,
    Author,
    Visitor,
    ;

    public static String getDefaultRoleStr() {
        return Visitor.name();
    }
}
