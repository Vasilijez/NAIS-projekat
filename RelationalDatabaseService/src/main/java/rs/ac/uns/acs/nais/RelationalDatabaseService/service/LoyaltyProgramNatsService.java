package rs.ac.uns.acs.nais.RelationalDatabaseService.service;

import io.nats.client.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.RelationalDatabaseService.dto.LoyaltyProgramEventDTO;
import rs.ac.uns.acs.nais.RelationalDatabaseService.model.LoyaltyProgram;

import java.nio.charset.StandardCharsets;

@Service
public class LoyaltyProgramNatsService {

    private Connection natsConnection;
    private final LoyaltyProgramService loyaltyProgramService;

    @Autowired
    public LoyaltyProgramNatsService(LoyaltyProgramService loyaltyProgramService) {
        this.loyaltyProgramService = loyaltyProgramService;
        initNatsConnection();
    }

    private void initNatsConnection() {
        try {
            natsConnection = Nats.connect("nats://nats:4222");
            subscribeToCreateLoyaltyProgramEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void subscribeToCreateLoyaltyProgramEvents() {
        try {
            Dispatcher dispatcher = natsConnection.createDispatcher((msg) -> {
                LoyaltyProgramEventDTO event = new Gson().fromJson(new String(msg.getData(), StandardCharsets.UTF_8), LoyaltyProgramEventDTO.class);
                handleLoyaltyProgramCreation(event.getLoyaltyProgram(), event.getRequestId());
            });
            dispatcher.subscribe("CREATE_LOYALTY_PROGRAM_IN_RELATIONAL_DB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void handleLoyaltyProgramCreation(LoyaltyProgram program, String requestId) {
        try {
            Boolean alreadyExists = loyaltyProgramService.saveLoyaltyProgramSAGA(program);

            if (alreadyExists == true) {
                publishLoyaltyProgramCreationResult(false, requestId);
            } else {
                publishLoyaltyProgramCreationResult(true, requestId);
            }
        } catch (Exception ex) {
            publishLoyaltyProgramCreationResult(false, requestId);
        }
    }

    private void publishLoyaltyProgramCreationResult(boolean isSuccess, String requestId) {
        String topic = isSuccess ? "LOYALTY_PROGRAM_FROM_RELATIONAL_DB_SUCCEED" : "LOYALTY_PROGRAM_FROM_RELATIONAL_DB_FAILED";
        String message = new Gson().toJson(requestId);
        try {
            natsConnection.publish(topic, message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}