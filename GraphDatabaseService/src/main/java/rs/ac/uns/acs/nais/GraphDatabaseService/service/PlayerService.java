package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import org.neo4j.driver.exceptions.DatabaseException;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q2DTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.PlayerRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.Q2DTORepository;

import java.awt.geom.QuadCurve2D;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final Q2DTORepository q2DTORepository;

    public PlayerService(PlayerRepository playerRepository, Q2DTORepository q2DTORepository) {
        this.playerRepository = playerRepository;
        this.q2DTORepository = q2DTORepository;
    }

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

    public Q2DTO query2(){
        return q2DTORepository.query2();
    }
}
