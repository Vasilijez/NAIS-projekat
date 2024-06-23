package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import org.neo4j.driver.exceptions.DatabaseException;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.MatchRepository;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> findAll(){
        return matchRepository.findAll();
    }

    public Match createMatch(Match match){
        return matchRepository.save(match);
    }

    public boolean deleteMatch(Long idOriginal){
        Match match = matchRepository.findByIdOriginal(idOriginal);

        if(match == null){
            return false;
        }

        try{
            matchRepository.delete(match);
        }
        catch(DatabaseException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
