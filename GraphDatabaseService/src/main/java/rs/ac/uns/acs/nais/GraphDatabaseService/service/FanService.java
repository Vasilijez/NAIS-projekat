package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import org.neo4j.driver.exceptions.DatabaseException;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Fan;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.FanRepository;

import java.util.List;

@Service
public class FanService {

    private final FanRepository fanRepository;

    public FanService(FanRepository fanRepository) {
        this.fanRepository = fanRepository;
    }

    public List<Fan> findAll(){
        return fanRepository.findAll();
    }

    public Fan createPlayer(Fan fan){
        return fanRepository.save(fan);
    }

    public boolean deletePlayer(Long idOriginal){
        Fan fan = fanRepository.findByIdOriginal(idOriginal);

        if(fan == null){
            return false;
        }

        try{
            fanRepository.delete(fan);
        }
        catch(DatabaseException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
