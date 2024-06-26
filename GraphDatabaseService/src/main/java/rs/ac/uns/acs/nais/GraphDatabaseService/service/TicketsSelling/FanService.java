package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Fan;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.FanRepository;

import java.util.Date;
import java.util.List;


@Service
public class FanService {

    private FanRepository fanRepository;

    public FanService(FanRepository fanRepository) {
        this.fanRepository = fanRepository;
    }

    public Fan createFan(Fan fan) {
        return fanRepository.createFan(fan.getUsername(), fan.getFanType(), fan.getFavouritePlayer(), fan.getDateOfBirth(), fan.getGender(), fan.getLevel());
    }

    public Fan findFan(String username) {
        return fanRepository.findFan(username);
    }

    public Fan updateFan(String username, Fan fan) {
        return fanRepository.updateFan(username, fan.getUsername(), fan.getFanType(), fan.getFavouritePlayer(), fan.getDateOfBirth(), fan.getGender(), fan.getLevel());
    }

    public boolean deleteFan(String username) {
        return fanRepository.deleteFan(username);
    }

    public boolean createCreatesOrderRelationship(String username, String orderID) {
        return fanRepository.createCreatesOrderRelationship(username, orderID);
    }

    public List<Order> findCreatesOrderRelationships(String username) {
        return fanRepository.findCreatesOrderRelationships(username);
    }

    public List<TwoBottomFansDTO> findTwoFansWithLowestPointsAndAtLeastOneOrderThisYear() {
        return fanRepository.findTwoFansWithLowestPointsAndAtLeastOneOrderThisYear();
    }





    public List <FanLoyaltyProgramPointsUpdatingDTO> updateFanLoyaltyProgramPoints() {
        return fanRepository.updateFanLoyaltyProgramPoints();
    }


}
