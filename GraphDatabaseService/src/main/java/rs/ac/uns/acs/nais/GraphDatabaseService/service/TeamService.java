package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import org.neo4j.driver.exceptions.DatabaseException;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q1DTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q3DTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q4DTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q5DTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Team;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.*;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final Q1DTORepository q1DTORepository;

    private final Q3DTORepository q3DTORepository;

    private final Q4DTORepository q4DTORepository;

    private final Q5DTORepository q5DTORepository;

    public TeamService(TeamRepository teamRepository, Q1DTORepository q1DTORepository, Q3DTORepository q3DTORepository, Q4DTORepository q4DTORepository, Q5DTORepository q5DTORepository) {
        this.teamRepository = teamRepository;
        this.q1DTORepository = q1DTORepository;
        this.q3DTORepository = q3DTORepository;
        this.q4DTORepository = q4DTORepository;
        this.q5DTORepository = q5DTORepository;
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

    public Q1DTO query1() {
        return q1DTORepository.query1();
    }

    public List<Q3DTO> query3() {
        return q3DTORepository.query3();
    }

    public List<Q4DTO> query4(){
        return q4DTORepository.query4();
    }

    public List<Q5DTO> query5() {
        return q5DTORepository.query5();
    }
}
