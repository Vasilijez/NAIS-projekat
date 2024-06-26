package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import org.neo4j.driver.exceptions.DatabaseException;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Team;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    public Team createTeam(Team team){
        return teamRepository.save(team);
    }

    public boolean deleteTeam(Long idOriginal){
        Team team = teamRepository.findByIdOriginal(idOriginal);

        if(team == null){
            return false;
        }

        try{
            teamRepository.delete(team);
        }
        catch(DatabaseException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
