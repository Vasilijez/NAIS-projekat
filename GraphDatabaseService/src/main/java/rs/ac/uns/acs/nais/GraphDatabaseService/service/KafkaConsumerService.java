package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TicketsSelling.KafkaMatchToGraphdbDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.TicketsSelling.FootballMatch;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TicketsSelling.FootballMatchRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class KafkaConsumerService {

    @Autowired
    private FootballMatchRepository footballMatchRepository;

    @KafkaListener(topics = "match_record", groupId = "group_id")
    public void consume(String jsonMessage) {
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

            // Convert to FootballMatch
            FootballMatch match = convertToFootballMatch(matchDto);

            // Save to the repository
            footballMatchRepository.save(match);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert from json", e);
        }
    }

    private FootballMatch convertToFootballMatch(KafkaMatchToGraphdbDTO matchDto) {
        FootballMatch newMatch = new FootballMatch();
        newMatch.setFootballMatchID(matchDto.getFootballMatchID());
        newMatch.setOpponentName(matchDto.getOpponentName());
        newMatch.setResult(matchDto.getResult());
        newMatch.setStartTime(matchDto.getStartTime());
        return newMatch;
    }
}
