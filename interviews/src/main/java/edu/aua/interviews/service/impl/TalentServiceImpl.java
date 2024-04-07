package edu.aua.interviews.service.impl;

import edu.aua.interviews.converter.TalentConverter;
import edu.aua.interviews.excaption.EmailNotFoundException;
import edu.aua.interviews.excaption.TalentNotFoundException;
import edu.aua.interviews.persistance.entity.Talent;
import edu.aua.interviews.persistance.repositories.TalentRepository;
import edu.aua.interviews.service.TalentService;
import edu.aua.interviews.service.dto.TalentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TalentServiceImpl implements TalentService {

    private final TalentRepository talentRepository;
    private final TalentConverter talentConverter;

    @Override
    @Transactional(readOnly = true)
    public Talent findById(Long id) {
        return this.talentRepository.findById(id)
                .orElseThrow(() -> new TalentNotFoundException("No talent found by this id", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Talent findTalentByEmail(String email) {

        return this.talentRepository.findTalentByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("No talent found by this email", email));
    }

    @Override
    @Transactional
    public Talent saveTalent(TalentDTO talentDTO) {
        final Talent talent = talentConverter.convertToEntity(talentDTO);
        return this.talentRepository.save(talent);
    }

    @Override
    public boolean existById(Long id) {
        return talentRepository.existsById(id);
    }

}
