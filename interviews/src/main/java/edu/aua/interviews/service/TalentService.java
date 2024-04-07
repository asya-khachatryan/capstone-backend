package edu.aua.interviews.service;

import edu.aua.interviews.excaption.EmailNotFoundException;
import edu.aua.interviews.excaption.TalentNotFoundException;
import edu.aua.interviews.persistance.entity.Talent;
import edu.aua.interviews.service.dto.TalentDTO;

public interface TalentService {

    Talent findById(Long id) throws TalentNotFoundException;

    Talent findTalentByEmail(String email) throws EmailNotFoundException;

    Talent saveTalent(TalentDTO talentDTO);

    boolean existById(Long id);
}
