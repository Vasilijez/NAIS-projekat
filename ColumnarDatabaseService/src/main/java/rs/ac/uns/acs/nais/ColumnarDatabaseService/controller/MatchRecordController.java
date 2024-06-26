package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.MatchRecordDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchRecord;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.MatchRecordService;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchRecordController {

    @Autowired
    private MatchRecordService matchRecordService;

    @GetMapping
    public List<MatchRecord> getAll(){
        return matchRecordService.getAll();
    }
    @PostMapping("/create")
    public ResponseEntity<MatchRecord> create(@RequestBody MatchRecordDTO dto){

        return ResponseEntity.ok(matchRecordService.create(dto));
    }
    @PostMapping("/create-list")
    public ResponseEntity<List<MatchRecord>> createRecordHistory(@RequestBody List<MatchRecordDTO> dtos){

        return ResponseEntity.ok(matchRecordService.createRecordList(dtos));
    }
}
