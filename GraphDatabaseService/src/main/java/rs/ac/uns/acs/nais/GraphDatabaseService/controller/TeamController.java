package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Team;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> findAll(){
        return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Team> createPlayer(@RequestBody Team team){
        return new ResponseEntity<>(teamService.createTeam(team), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deletePlayer(@RequestParam Long id){
        if(teamService.deleteTeam(id)){
            return new ResponseEntity<>(true, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
