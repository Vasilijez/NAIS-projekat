package rs.ac.uns.acs.nais.RelationalDatabaseService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.RelationalDatabaseService.model.LoyaltyProgram;

@Repository
public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, String>{



}
