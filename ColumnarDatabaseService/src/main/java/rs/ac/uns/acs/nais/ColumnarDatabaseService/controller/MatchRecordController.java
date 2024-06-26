package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.KafkaMatchToGraphdbDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.MatchRecordDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchRecord;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchRecordPrimaryKey;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.KafkaProducerService;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.MatchRecordService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchRecordController {

    @Autowired
    private MatchRecordService matchRecordService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping
    public List<MatchRecord> getAll(){
        return matchRecordService.getAll();
    }
    @PostMapping("/create")
    public ResponseEntity<MatchRecord> create(@RequestBody MatchRecordDTO dto) {
        MatchRecord matchRecord = matchRecordService.create(dto);
        KafkaMatchToGraphdbDTO matchDto = new KafkaMatchToGraphdbDTO(matchRecord);

        try {
            // Define the date-time format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            // Create a module and add the serializer
            JavaTimeModule module = new JavaTimeModule();
            module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));

            // Create ObjectMapper and register the module
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(module);

            // Serialize to JSON
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(matchDto);
            kafkaProducerService.sendMessage(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert to json", e);
        }
        return ResponseEntity.ok(matchRecord);
    }

    @PostMapping("/create-list")
    public ResponseEntity<List<MatchRecord>> createRecordHistory(@RequestBody List<MatchRecordDTO> dtos){

        return ResponseEntity.ok(matchRecordService.createRecordList(dtos));
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody MatchRecordPrimaryKey primaryKey){
         matchRecordService.delete(primaryKey);
    }
}
