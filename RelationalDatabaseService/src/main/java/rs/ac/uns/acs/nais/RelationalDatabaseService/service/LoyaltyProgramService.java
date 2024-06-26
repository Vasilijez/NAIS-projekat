package rs.ac.uns.acs.nais.RelationalDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.RelationalDatabaseService.model.LoyaltyProgram;
import rs.ac.uns.acs.nais.RelationalDatabaseService.repository.LoyaltyProgramRepository;

import java.util.Optional;

@Service
public class LoyaltyProgramService {

    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;

    public LoyaltyProgram save(LoyaltyProgram loyaltyProgram) {
        return loyaltyProgramRepository.save(loyaltyProgram);
    }

    public Boolean saveLoyaltyProgramSAGA(LoyaltyProgram loyaltyProgram) {
        Optional<LoyaltyProgram> alreadyExists = loyaltyProgramRepository.findById(loyaltyProgram.getLevel());

        // Da li sadrzi vrednost ili ne
        if (alreadyExists.isPresent() == true) {
            return true;
        }

        loyaltyProgramRepository.save(loyaltyProgram);

        return false;
    }

}
