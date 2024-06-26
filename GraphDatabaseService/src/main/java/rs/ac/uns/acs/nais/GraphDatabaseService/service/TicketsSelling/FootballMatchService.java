package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.FiveLatestFootballMatchesDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.PopularFootballMatchDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.FootballMatch;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Seat;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.FiveLatestFootballMatchesRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.FootballMatchRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.SeatRepository;

import java.util.List;


@Service
public class FootballMatchService {

    private FootballMatchRepository footballMatchRepository;

    private FiveLatestFootballMatchesRepository fiveLatestFootballMatchesRepository;

    public FootballMatchService(FootballMatchRepository footballMatchRepository, FiveLatestFootballMatchesRepository fiveLatestFootballMatchesRepository) {
        this.footballMatchRepository = footballMatchRepository;
        this.fiveLatestFootballMatchesRepository = fiveLatestFootballMatchesRepository;
    }

    public Boolean createFootballMatch(FootballMatch footballMatch) {
        return footballMatchRepository.createFootballMatch(footballMatch.getFootballMatchID(), footballMatch.getStartTime(), footballMatch.getOpponentName(), footballMatch.getResult());
    }

    public FootballMatch findFootballMatch(String footballMatchID) {
        return footballMatchRepository.findFootballMatch(footballMatchID);
    }

    public FootballMatch updateFootballMatch(String footballMatchID, FootballMatch footballMatch) {
        return footballMatchRepository.updateFootballMatch(footballMatchID, footballMatch.getFootballMatchID(), footballMatch.getStartTime(), footballMatch.getOpponentName(), footballMatch.getResult());
    }

    public boolean deleteFootballMatch(String footballMatchID) {
        return footballMatchRepository.deleteFootballMatch(footballMatchID);
    }

//    public List<FiveLatestFootballMatchesDTO> getTicketsStatsFromFiveLatestFootballMatches() {
//        return fiveLatestFootballMatchesRepository.getTicketsStatsFromFiveLatestFootballMatches();
//    }
//
    public List<FiveLatestFootballMatchesDTO> getTicketsStatsFromFiveLatestFootballMatches() {
        return footballMatchRepository.getTicketsStatsFromFiveLatestFootballMatches();
    }

    public List<PopularFootballMatchDTO> recommendPopularFootballMatch(String fanUsername) {
        return footballMatchRepository.recommendPopularFootballMatch(fanUsername);
    }
}
