package entities;

public class Player extends TeamMember {
    private int number;
    private int age;
    private int position;

    public Player(String name, String surname, String pesel, int number, int age, int position) {
        super(name, surname, pesel);
        this.number = number;
        this.age = age;
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public int getAge() {
        return age;
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        return super.toString() + " " + this.number + " " + this.age + " " + this.position;
    }
}
