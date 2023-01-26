package entities;

public class TechnicalMember extends TeamMember {
    private String role;

    public TechnicalMember(String name, String surname, String pesel, String role) {
        super(name, surname, pesel);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String toString() {
        return super.toString() + " " + this.role;
    }
}
