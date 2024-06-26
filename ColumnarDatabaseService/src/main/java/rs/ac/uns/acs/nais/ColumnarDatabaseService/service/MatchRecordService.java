package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.MatchRecordDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchRecord;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.MatchRecordPrimaryKey;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.IncomeRepository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.MatchRecordRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@EnableCaching
public class MatchRecordService {

    @Autowired
    private MatchRecordRepository matchRecordRepository;

    public List<MatchRecord> getAll() {
        return matchRecordRepository.findAll();
    }

    public MatchRecord create(MatchRecordDTO dto) {
        MatchRecord newMatch = new MatchRecord();
        newMatch.setId(UUID.randomUUID());
        newMatch.setLocation(dto.getLocation());
        newMatch.setDateTimeStarted(dto.getDateTimeStarted());
        newMatch.setOpponentName(dto.getOpponentName());
        newMatch.setScore(dto.getScore());
        newMatch.setType(dto.getType());

        return matchRecordRepository.save(newMatch);
    }

    public List<MatchRecord> createRecordList(List<MatchRecordDTO> dtos) {
        List<MatchRecord> newMatches = new ArrayList<>();

        for (MatchRecordDTO dto: dtos) {
            MatchRecord newMatch = new MatchRecord();
            newMatch.setId(UUID.randomUUID());
            newMatch.setLocation(dto.getLocation());
            newMatch.setDateTimeStarted(dto.getDateTimeStarted());
            newMatch.setOpponentName(dto.getOpponentName());
            newMatch.setScore(dto.getScore());
            newMatch.setType(dto.getType());

            newMatches.add(newMatch);
        }
        return matchRecordRepository.saveAll(newMatches);
    }

    public void delete(MatchRecordPrimaryKey primaryKey) {

        MatchRecord matchRecord = matchRecordRepository.findByIdAndSeason(primaryKey.getId(), primaryKey.getSeason());
         matchRecordRepository.delete(matchRecord);
    }
}


