package edu.aua.onboardingservice.service.impl;

import edu.aua.onboardingservice.converter.MentorConverter;
import edu.aua.onboardingservice.exception.MentorNotFoundException;
import edu.aua.onboardingservice.persistance.MentorRepository;
import edu.aua.onboardingservice.persistance.entity.Mentor;
import edu.aua.onboardingservice.service.MentorService;
import edu.aua.onboardingservice.service.dto.MentorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MentorServiceImpl implements MentorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MentorServiceImpl.class);

    private final MentorRepository mentorRepository;
    private final MentorConverter mentorConverter;

    public MentorServiceImpl(MentorRepository mentorRepository, MentorConverter mentorConverter) {
        this.mentorRepository = mentorRepository;
        this.mentorConverter = mentorConverter;
    }

    @Override
    public List<Mentor> findALl() {
        LOGGER.info("In findAll Mentor requested to get all mentros");
        return this.mentorRepository.findAll();
    }

    @Override
    public Mentor findById(Long id) {
        LOGGER.info("In findById Mentor requested to get the mentor with id {}", id);
        return this.mentorRepository.findById(id)
                .orElseThrow(() -> new MentorNotFoundException("No mentor found by this id", id));
    }

    @Override
    public Mentor create(MentorDto mentorDTO) {
        final Mentor mentor = mentorConverter.convertToEntity(mentorDTO);
        return mentorRepository.save(mentor);
    }

    @Override
    public Mentor update(Long id, MentorDto mentorDTO) {
        final Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new MentorNotFoundException("No mentor found by this id", id));
        mentor.setFirstName(mentorDTO.getFirstName());
        mentor.setLastName(mentorDTO.getLastName());
        mentor.setEmail(mentorDTO.getEmail());
        mentor.setPhoneNumber(mentorDTO.getPhoneNumber());
        mentor.setDisplayName(mentorDTO.getDisplayName());
        return mentorRepository.save(mentor);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        LOGGER.info("Requested to delete a mentor with id {}", id);
        if (!mentorRepository.existsById(id)) {
            throw new MentorNotFoundException("No mentor found by this id", id);
        }
        mentorRepository.deleteById(id);
        LOGGER.info("In deleteById Mentor mentor with id {} successfully deleted", id);
        return true;
    }
}
