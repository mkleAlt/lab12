package modules;

import entities.Player;
import entities.TeamMember;
import entities.TechnicalMember;

import java.util.List;
import java.util.function.Predicate;

public class Team {
    private TeamRepository teamRepository;

    public Team(String repoName) {
        teamRepository = new TeamRepository(repoName);
    }

    public boolean addTeamMember(TeamMember teamMember) {
        for(TeamMember member : teamRepository.getAll()) {
            if(member.getPesel().equals(teamMember.getPesel())) {
                return false;
            }
        }
        return teamRepository.add(teamMember);
    }

    public List<TeamMember> getTeamMembers() {
        Predicate<TeamMember> teamMemberPredicate = object -> object instanceof TeamMember;
        return teamRepository.filter(teamMemberPredicate);
    }

    public List<TeamMember> getPlayers() {
        Predicate<TeamMember> playerPredicate = object -> object instanceof Player;
        return teamRepository.filter(playerPredicate);
    }

    public List<TeamMember> getTechnicalMembers() {
        Predicate<TeamMember> technicalMemberPredicate = object -> object instanceof TechnicalMember;
        return teamRepository.filter(technicalMemberPredicate);
    }

    public TeamMember getTeamMember(String pesel) {
        for(TeamMember member : teamRepository.getAll()) {
            if(member.getPesel().equals(pesel)) {
                return member;
            }
        }
        return null;
    }

    public boolean removeTeamMember(String pesel) {
        return teamRepository.remove(pesel);
    }

    public boolean updateTeamMember(String oldPesel, TeamMember newMember) {
        return teamRepository.update(oldPesel, newMember);
    }

    public List<TeamMember> getSortedTeamMembers() {
        TeamMember[] teamMembers = new TeamMember[teamRepository.getAll().size()];
        teamMembers = teamRepository.getAll().toArray(teamMembers);

        for(int i = 0; i < teamMembers.length; i++) {
            for(int j = 0; j < teamMembers.length - 1; j++) {
                if(teamMembers[j].compareTo(teamMembers[j + 1]) > 0) {
                    TeamMember temp = teamMembers[j];
                    teamMembers[j] = teamMembers[j + 1];
                    teamMembers[j + 1] = temp;
                }
            }
        }

        List<TeamMember> sortedTeamMembers = List.of(teamMembers);
        return sortedTeamMembers;
    }

    public String toString() {
        return teamRepository.toString();
    }
}
