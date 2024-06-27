package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.KafkaMatchToGraphdbDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchRecord;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchRecordPrimaryKey;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.MatchRecordRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class KafkaConsumerService {

    @Autowired
    private MatchRecordService matchRecordService;

    @KafkaListener(topics = "delete_match", groupId = "group_id")
    public void consumeDeleteNotification(String jsonMessage) {
        try {
            // Define the date-time format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            // Create a module and add the deserializer
            JavaTimeModule module = new JavaTimeModule();
            module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));

            // Create ObjectMapper and register the module
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(module);

            // Deserialize from JSON
            KafkaMatchToGraphdbDTO matchDto = mapper.readValue(jsonMessage, KafkaMatchToGraphdbDTO.class);

            // Find and delete the match record from the columnar database
            MatchRecordPrimaryKey primaryKey = new MatchRecordPrimaryKey(matchDto.getStartTime().getYear(), UUID.fromString(matchDto.getFootballMatchID()));
            matchRecordService.delete(primaryKey);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert from json", e);
        }
    }
}
