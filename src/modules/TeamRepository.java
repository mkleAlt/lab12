package modules;

import entities.TeamMember;
import exceptions.ValidationException;
import interfaces.RepositoryInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TeamRepository implements RepositoryInterface<TeamMember>, Serializable {
    private ArrayList<TeamMember> teamMembers;
    private String fileName;

    public static class IORepositoryException extends RuntimeException {
        public IORepositoryException(String message)
        {
            super(message);
        }
    }

    public TeamRepository(String fileName) throws ValidationException {
        if(fileName == null || fileName == "") {
            throw new ValidationException("File name not specified");
        }
        this.fileName = fileName;
        if(!repositoryExists()) {
            teamMembers = new ArrayList<>();
            save();
        }
        try{
            read();
        } catch (IORepositoryException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    protected boolean repositoryExists() {
        File file = new File(fileName);
        return file.exists();
    }

    protected void read() throws IORepositoryException {
        teamMembers = new ArrayList<>();
        try {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(fileName)
            );
            teamMembers = (ArrayList<TeamMember>) input.readObject();
            input.close();
        } catch (java.io.IOException e) {
            throw new IORepositoryException("Error reading data from file");
        } catch (java.lang.ClassNotFoundException e) {
            throw new IORepositoryException("Wrong class in file");
        }
    }

    protected void save() throws IORepositoryException {
        try {
            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream(fileName)
            );
            output.writeObject(teamMembers);
            output.close();
        } catch (java.io.IOException e) {
            throw new IORepositoryException("Error writing data to file");
        }
    }

    @Override
    public boolean add(TeamMember object) {
        boolean result = teamMembers.add(object);
        if(result) {
            try {
                save();
            } catch (IORepositoryException e) {
                return false;
            }
        }
        return result;
    }

    @Override
    public boolean update(String pesel, TeamMember newMember) {
        boolean result = teamMembers.removeIf(obj -> obj.getPesel().equals(pesel));
        if(result) {
            boolean finalResult = teamMembers.add(newMember);
            System.out.println("fr: " + result);
            if(finalResult) {
                try {
                    save();
                } catch (IORepositoryException e) {
                    return false;
                }
            }
            return result;
        }
        return false;
    }

    @Override
    public List<TeamMember> getAll() {
        return List.copyOf(teamMembers);
    }

    @Override
    public List<TeamMember> filter(Predicate<TeamMember> predicate) {
        return teamMembers.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public boolean remove(String pesel) {
        boolean result = teamMembers.removeIf(obj -> obj.getPesel().equals(pesel));
        if(result) {
            try {
                save();
            } catch (IORepositoryException e) {
                return false;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for(TeamMember member : teamMembers) {
            result += member.toString() + "\n";
        }
        return result;
    }
}
