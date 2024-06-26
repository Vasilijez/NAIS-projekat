package rs.ac.uns.acs.nais.ColumnarDatabaseService.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

public class MatchRecordPrimaryKey implements Serializable {
    @PrimaryKeyColumn(name = "season", type = PrimaryKeyType.PARTITIONED)
    private Integer season;

    @PrimaryKeyColumn(name = "match_id", type = PrimaryKeyType.CLUSTERED)
    private UUID id;

    public MatchRecordPrimaryKey() {
    }

    public MatchRecordPrimaryKey(Integer season, UUID id) {
        this.season = season;
        this.id = id;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
