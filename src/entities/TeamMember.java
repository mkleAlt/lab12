package entities;

import java.io.Serializable;

public abstract class TeamMember implements Serializable {

    private String name;
    private String surname;
    private String pesel;

    public TeamMember(String name, String surname, String pesel) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }

    public int compareTo(TeamMember other) {
        return this.pesel.compareTo(other.pesel);
    }

    public boolean equals(TeamMember other) {
        return this.pesel.equals(other.pesel);
    }

    public int hashCode() {
        return this.pesel.hashCode();
    }

    public String toString() {
        return this.name + " " + this.surname + " " + this.pesel;
    }
}
