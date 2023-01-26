import entities.Player;
import entities.TechnicalMember;
import modules.Team;

public class Main {
    public static void main(String[] args) {
        // UWAGA - trzeba zmienić nazwę repo na swoją
        // inaczej test nie będzie w 100% poprawny
        // np. "team1" -> "team2"

        System.out.println("Testy dla klasy Team:");
        System.out.println("1. Inicjalizowanie druzyny...");
        Team team = new Team("team12");

        System.out.println("2. Dodawanie zawodnika: " + team.addTeamMember(new Player("A", "Aski", "12345678901", 1, 20, 1)));
        System.out.println("2.A. Dodawanie tego samego zawodnika: " + team.addTeamMember(new Player("A", "Aski", "12345678901", 1, 20, 1)));
        System.out.println("2.B. Dodawanie pracownika technicznego: " + team.addTeamMember(new TechnicalMember("a", "aski", "12345678902", "trener")));
        System.out.println("2.C. Dalsze uzupełnianie drużyny...");
        team.addTeamMember(new Player("B", "bski", "12345678905", 2, 34, 2));
        team.addTeamMember(new Player("C", "cski", "12345678903", 3, 18, 3));
        team.addTeamMember(new Player("D", "dski", "12345678913", 4, 21, 4));
        team.addTeamMember(new Player("E", "eski", "12345678908", 5, 37, 5));
        team.addTeamMember(new TechnicalMember("b", "bski", "12345678907", "sprzatacz"));
        System.out.println("2.D. Druzyna po wszystkich dodaniach:\n" + team);

        System.out.println("3. Testowanie metod zwracajacych filtrowane listy: "
            + "\na) getTeamMembers(): " + team.getTeamMembers()
            + "\nb) getPlayers(): " + team.getPlayers()
            + "\nc) getTechnicalMembers(): " + team.getTechnicalMembers());

        System.out.println("4. Zwrocenie czlonka o peselu 12345678901: " + team.getTeamMember("12345678901"));
        System.out.println("4.A. Zwrocenie nieobecnego czlonka: " + team.getTeamMember("12345678900"));

        System.out.println("5. Usuniecie czlonka o peselu 12345678901: " + team.removeTeamMember("12345678901"));
        System.out.println("5.A. Usuniecie nieobecnego czlonka: " + team.removeTeamMember("12345678900"));

        System.out.println("6. Aktualizacja czlonka o peselu 12345678902 nowym czlonkiem: "
                + team.updateTeamMember(
                        "12345678902",
                    new TechnicalMember("x", "xski", "12345678947", "cos")
                ));

        System.out.println("7. Zwrocenie posortowanej druzyny: " + team.getSortedTeamMembers());
    }
}