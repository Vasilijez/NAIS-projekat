package rs.ac.uns.acs.nais.GraphDatabaseService.controller.TicketsSelling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.FiveLatestFootballMatchesDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.PopularFootballMatchDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.FootballMatch;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;
import rs.ac.uns.acs.nais.GraphDatabaseService.report.ReportGenerator;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.FanService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.FootballMatchService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.OrderService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling.SeatService;

import java.util.List;

@RestController
@RequestMapping("/football-match")
public class FootballMatchController {

    @Autowired
    private FootballMatchService footballMatchService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FanService fanService;

    @Autowired
    private  SeatService seatService;

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<String> createFootballMatch(@RequestBody FootballMatch footballMatch) {
        Boolean isCreated = footballMatchService.createFootballMatch(footballMatch);
        if (isCreated) {
            return ResponseEntity.ok("Football match is created!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Football match already exists!");
        }
    }

    @GetMapping("/{footballMatchID}")
    public ResponseEntity<String> findFootballMatch(@PathVariable String footballMatchID) {
        FootballMatch node = footballMatchService.findFootballMatch(footballMatchID);
        if (node != null) {
            return ResponseEntity.ok("Football match found: " + node.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Football match with id '" + footballMatchID + "' not found.");
        }
    }

    @PutMapping(value = "/{footballMatchID}", consumes = "application/json")
    public ResponseEntity<String> updateFootballMatch(@PathVariable String footballMatchID, @RequestBody FootballMatch footballMatch) {
        FootballMatch updatedNode = footballMatchService.updateFootballMatch(footballMatchID, footballMatch);
        if (updatedNode != null) {
            return ResponseEntity.ok("Football match updated successfully: " + updatedNode.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        }
    }

    @DeleteMapping("/{footballMatchID}")
    public ResponseEntity<String> deleteFootballMatch(@PathVariable String footballMatchID){
        if (footballMatchService.deleteFootballMatch(footballMatchID)) {
            return ResponseEntity.ok("Football match with id '" + footballMatchID + "' was successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Football match with id '" + footballMatchID + "' not found.");
        }
    }

    @GetMapping("/getTicketsStatsFromFiveLatestFootballMatches")
    public ResponseEntity<String> getTicketsStatsFromFiveLatestFootballMatches() {
        List<FiveLatestFootballMatchesDTO> nodes = footballMatchService.getTicketsStatsFromFiveLatestFootballMatches();
        if (nodes != null) {
            return ResponseEntity.ok("Football matches are found: " + nodes.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Football matches are not found.");
        }
    }

    @GetMapping("/recommendPopularFootballMatch/{fanUsername}")
    public ResponseEntity<String> recommendPopularFootballMatch(@PathVariable String fanUsername) {
        List<PopularFootballMatchDTO> nodes = footballMatchService.recommendPopularFootballMatch(fanUsername);
        if (nodes != null) {
            return ResponseEntity.ok("Popular football matches are found " + nodes.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Popular football matches are not found " + nodes.toString());
        }
    }

    @GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportPdf(
            @RequestParam(value = "fanUsername") String username) {

        ReportGenerator reportGenerator = new ReportGenerator(orderService, fanService, footballMatchService, seatService);
        byte[] pdfContents = reportGenerator.generateReport(username);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContents);
    }
}

