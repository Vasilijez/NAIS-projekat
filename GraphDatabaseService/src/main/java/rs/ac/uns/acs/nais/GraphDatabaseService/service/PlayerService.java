package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import org.neo4j.driver.exceptions.DatabaseException;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public Player createPlayer(Player player){
        return playerRepository.save(player);
    }

    public boolean deletePlayer(Long idOriginal){
        Player player = playerRepository.findByIdOriginal(idOriginal);

        if(player == null){
            return false;
        }

        try{
            playerRepository.delete(player);
        }
        catch(DatabaseException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

//    public boolean updatePlayer


}
