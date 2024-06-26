package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Match;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.MatchService;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public ResponseEntity<List<Match>> findAll(){
        return new ResponseEntity<>(matchService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match){
        return new ResponseEntity<>(matchService.createMatch(match), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteMatch(@RequestParam Long id){
        if(matchService.deleteMatch(id)){
            return new ResponseEntity<>(true, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

}
