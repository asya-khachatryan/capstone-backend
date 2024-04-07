package edu.aua.onboardingservice.service.impl;

import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectResponseDto;
import edu.aua.onboardingservice.exception.RoadmapNotFoundException;
import edu.aua.onboardingservice.persistance.RoadmapRepository;
import edu.aua.onboardingservice.persistance.entity.Roadmap;
import edu.aua.onboardingservice.persistance.enums.RoadmapStatus;
import edu.aua.onboardingservice.service.MenteeService;
import edu.aua.onboardingservice.service.MentorService;
import edu.aua.onboardingservice.service.RoadmapService;
import edu.aua.onboardingservice.service.dto.RoadmapDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class RoadmapServiceImpl implements RoadmapService {

    private static final Logger log = LoggerFactory.getLogger(RoadmapServiceImpl.class);

    private final RoadmapRepository roadmapRepository;
    private final MenteeService menteeService;
    private final MentorService mentorService;

    public RoadmapServiceImpl(final RoadmapRepository roadmapRepository,
                              final MenteeService menteeService,
                              final MentorService mentorService) {
        this.roadmapRepository = roadmapRepository;
        this.menteeService = menteeService;
        this.mentorService = mentorService;
    }

    @Override
    public List<Roadmap> findALl() {
        log.info("In findAll Roadmap requested to get all roadmaps");
        return this.roadmapRepository.findAll();
    }

    @Override
    public Roadmap findById(Long id) {
        log.info("In findById Roadmap requested to get the roadmap with id {}", id);
        return this.roadmapRepository.findById(id)
                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this id", id));
    }

    @Override
    public Roadmap create(final RoadmapDto roadmapDTO, final ProjectResponseDto jiraProject) {
        log.info("Requested to create a roadmap");
        roadmapDTO.setJiraProjectKey(jiraProject.getKey());
        final Roadmap roadmap = buildFrom(roadmapDTO);
        roadmap.setStatus(RoadmapStatus.STARTED);
        log.info("In create Roadmap roadmap successfully created");
        return roadmapRepository.save(roadmap);
    }

    @Override
    @Transactional
    public Roadmap update(Long id, RoadmapDto roadmapDTO) {
        log.info("Requested to update a talent with id {}", id);
        final Roadmap roadmap = this.roadmapRepository.findById(id)
                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this id", id));
        extracteRoadMap(roadmapDTO, roadmap);
        log.info("In update Roadmap roadmap with id {} successfully updated", id);
        return roadmapRepository.save(roadmap);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.info("Requested to delete a roadmap with id {}", id);
        if (!roadmapRepository.existsById(id)) {
            throw new RoadmapNotFoundException("No roadmap found by this id", id);
        }
        roadmapRepository.deleteById(id);
        log.info("In deleteById Roadmap roadmap with id {} successfully deleted", id);
        return true;
    }

    @Override
    public Roadmap updateStatus(Long id, String status) {
        log.info("Requested to update status to {} of a roadmap with id {}", status, id);
        final Roadmap roadmap = this.roadmapRepository.findById(id)
                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this id", id));
        roadmap.setStatus(RoadmapStatus.valueOf(status.toUpperCase(Locale.ROOT)));
        log.info("In updateStatus Roadmap the status of roadmap with id {} successfully updated to {}", id, status);
        return roadmapRepository.save(roadmap);
    }

    @Override
    public Roadmap findByProjectKey(String projectKey) {
        return this.roadmapRepository.findByJiraProjectKey(projectKey)
                .orElseThrow(() -> new RoadmapNotFoundException("No roadmap found by this key", projectKey));
    }

    private Roadmap buildFrom(final RoadmapDto roadmapDto) {
        final Roadmap roadmap = new Roadmap();
        extracteRoadMap(roadmapDto, roadmap);

        return roadmap;
    }

    private void extracteRoadMap(final RoadmapDto roadmapDto, final Roadmap roadmap) {
        roadmap.setStartDate(roadmapDto.getStartDate());
        roadmap.setJiraProjectKey(roadmapDto.getJiraProjectKey());
        roadmap.setEndDate(roadmapDto.getEndDate());
        roadmap.setDescription(roadmapDto.getDescription());
        roadmap.setName(roadmapDto.getName());
        roadmap.setMentee(menteeService.findById(roadmapDto.getMenteeId()));
        roadmap.setMentor(mentorService.findById(roadmapDto.getMentorId()));
    }
}
