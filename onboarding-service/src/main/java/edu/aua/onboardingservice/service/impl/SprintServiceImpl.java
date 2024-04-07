package edu.aua.onboardingservice.service.impl;

import edu.aua.onboardingservice.client.mailclient.MailGenerator;
import edu.aua.onboardingservice.client.mailclient.MailSenderClient;
import edu.aua.onboardingservice.exception.SprintNotFoundException;
import edu.aua.onboardingservice.persistance.SprintRepository;
import edu.aua.onboardingservice.persistance.entity.Sprint;
import edu.aua.onboardingservice.service.RoadmapService;
import edu.aua.onboardingservice.service.SprintService;
import edu.aua.onboardingservice.service.dto.SprintDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SprintServiceImpl implements SprintService {

    private final static Logger log = LoggerFactory.getLogger(SprintServiceImpl.class);

    private final RoadmapService roadmapService;
    private final SprintRepository sprintRepository;
    private final MailSenderClient mailSenderClient;
    private final MailGenerator mailGenerator;

    public SprintServiceImpl(RoadmapService roadmapService, SprintRepository sprintRepository,
                             MailSenderClient mailSenderClient, MailGenerator mailGenerator) {
        this.roadmapService = roadmapService;
        this.sprintRepository = sprintRepository;
        this.mailSenderClient = mailSenderClient;
        this.mailGenerator = mailGenerator;
    }

    @Override
    public List<Sprint> findALl() {
        return null;
    }

    @Override
    public Sprint findById(Long id) {
        return null;
    }

    @Override
    public Sprint create(SprintDto sprintDto) {
        return sprintRepository.save(buildSprintFrom(sprintDto));
    }

    @Override
    public Sprint update(Long id, SprintDto sprintDto) {
        final Sprint sprint = this.sprintRepository.findById(id)
                .orElseThrow(() -> new SprintNotFoundException("Not found sprint by id ", id));
        sprint.setName(sprintDto.getName());
        sprint.setStartDate(sprintDto.getStartDate());
        sprint.setEndDate(sprintDto.getEndDate());
        return this.sprintRepository.save(sprint);
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    private Sprint buildSprintFrom(final SprintDto sprintDto) {
        final Sprint sprint = new Sprint();
        sprint.setStartDate(sprintDto.getStartDate());
        sprint.setEndDate(sprintDto.getEndDate());
        sprint.setRoadmap(this.roadmapService.findById(sprintDto.getRoadMapId()));
        sprint.setName(sprintDto.getName());
        return sprint;
    }

    @Override
    public boolean isTodayTheEmailDate(Sprint sprint) {
        return sprint.getEndDate().equals(LocalDate.now());
    }

    @Scheduled(cron = "0 0 14 * * ?", zone = "GMT+4")
    @Override
    public void sendSprintCheckupEmail() {
        final List<Sprint> allSprints = this.findALl();
        for (Sprint sprint : allSprints) {
            if (this.isTodayTheEmailDate(sprint)) {
                mailSenderClient.sendEmail(mailGenerator.sprintCheckupMailGenerator(sprint.getRoadmap().getMentee()));
            }
        }
    }
}
