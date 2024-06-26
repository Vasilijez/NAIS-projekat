package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Q2DTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Player;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> findAll(){
        return new ResponseEntity<>(playerService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player){
        return new ResponseEntity<>(playerService.createPlayer(player), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deletePlayer(@RequestParam Long id){
        if(playerService.deletePlayer(id)){
            return new ResponseEntity<>(true, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/q2")
    public ResponseEntity<Q2DTO> query2(){
        return new ResponseEntity<>(playerService.query2(), HttpStatus.OK);
    }
}
