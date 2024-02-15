package cat.copernic.backend.data.enums;

public enum UserRole {
    STUDENT,
    COMPANY,
    ADMINISTRATOR;

    @Override
    public String toString() {
        return name();
    }
}
