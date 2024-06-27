package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.Order;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.TransactionStatus;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.LoyaltyProgramRepository;

import java.util.List;


@Service
public class LoyaltyProgramService {

    @Autowired
    private TransactionManagerService transactionManagerService;

    @Autowired
    private LoyaltyProgramNatsService loyaltyProgramNatsService;

    private LoyaltyProgramRepository loyaltyProgramRepository;

    public LoyaltyProgramService(LoyaltyProgramRepository loyaltyProgramRepository) {
        this.loyaltyProgramRepository = loyaltyProgramRepository;
    }

    public LoyaltyProgram createLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        return loyaltyProgramRepository.createLoyaltyProgram(loyaltyProgram.getLevel(), loyaltyProgram.getDiscountRate());
    }

    public String createLoyaltyProgramSAGA(LoyaltyProgram loyaltyProgramParameter) {
        String requestId = RandomStringUtils.randomAlphanumeric(10);

        transactionManagerService.beginTransaction(requestId);

        Boolean isCreated = transactionManagerService.createLoyaltyProgram(requestId, loyaltyProgramParameter.getLevel(), loyaltyProgramParameter.getDiscountRate());

        // Izlaz odmah na pocetku - neuspesan upis u Neo4J bazu.
        if (isCreated == false) {
            transactionManagerService.rollbackTransaction(requestId);
        } else {
            loyaltyProgramNatsService.publishCreateEvent("CREATE_LOYALTY_PROGRAM_IN_RELATIONAL_DB", requestId, loyaltyProgramParameter);
        }

        return String.format("The request is successfully delivered! In the meantime, check the status of the request onto: %n" +
                "http://localhost:9003/graph-database-service/loyalty-program/saga/checkStatus/%s %n" +
                "Instead of the 'YOUR_REQUEST_ID' insert: %s", requestId, requestId);
    }

    public TransactionStatus checkStatus(String requestId) {
        return transactionManagerService.getStatus(requestId);
    }

    public LoyaltyProgram getLoyaltyProgramByLevel(String level) {
        return loyaltyProgramRepository.findLoyaltyProgram(level);
    }

    public LoyaltyProgram updateLoyaltyProgram(String level, LoyaltyProgram loyaltyProgram) {
        return loyaltyProgramRepository.updateLoyaltyProgram(level, loyaltyProgram.getLevel(), loyaltyProgram.getDiscountRate());
    }

    public boolean deleteLoyaltyProgram(String level) {
        return loyaltyProgramRepository.deleteLoyaltyProgram(level);
    }

    public List<LoyaltyProgram> findMemberOfRelationship(String username) {
        return loyaltyProgramRepository.findMemberOfRelationship(username);
    }

}
