package rs.ac.uns.acs.nais.GraphDatabaseService.service.TicketsSelling;

import com.google.gson.Gson;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nats.client.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.LoyaltyProgramEventDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.LoyaltyProgram;

import java.nio.charset.StandardCharsets;

@Service
public class LoyaltyProgramNatsService {

    private Connection natsConnection;

    @Autowired
    private TransactionManagerService transactionManagerService;

    @Autowired
    public LoyaltyProgramNatsService() {
        initNatsConnection();
    }

    private void initNatsConnection() {
        try {
            natsConnection = Nats.connect("nats://nats:4222");
            subscribeToLoyaltyProgramResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publishCreateEvent(String subject, String requestId, LoyaltyProgram loyaltyProgram) {
        try {
            String message = new Gson().toJson(new LoyaltyProgramEventDTO(loyaltyProgram, requestId));
            natsConnection.publish(subject, message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void subscribeToLoyaltyProgramResults() {
        try {
            Dispatcher dispatcher = natsConnection.createDispatcher((msg) -> {
                if ("LOYALTY_PROGRAM_FROM_RELATIONAL_DB_SUCCEED".equals(msg.getSubject())) {
                    String requestId = new Gson().fromJson(new String(msg.getData(), StandardCharsets.UTF_8), String.class);
                    handleSuccess(requestId);
                } else if ("LOYALTY_PROGRAM_FROM_RELATIONAL_DB_FAILED".equals(msg.getSubject())) {
                    String requestId = new Gson().fromJson(new String(msg.getData(), StandardCharsets.UTF_8), String.class);
                    handleFailure(requestId);
                }
            });
            dispatcher.subscribe("LOYALTY_PROGRAM_FROM_RELATIONAL_DB_SUCCEED");
            dispatcher.subscribe("LOYALTY_PROGRAM_FROM_RELATIONAL_DB_FAILED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleSuccess(String requestId) {
        transactionManagerService.commitTransaction(requestId);
    }

    private void handleFailure(String requestId) {
        transactionManagerService.rollbackTransaction(requestId);
    }
}

