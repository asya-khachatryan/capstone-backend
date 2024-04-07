package edu.aua.onboardingservice.service;

import edu.aua.onboardingservice.persistance.entity.Sprint;
import edu.aua.onboardingservice.service.dto.SprintDto;

import java.util.List;

public interface SprintService {

    List<Sprint> findALl();

    Sprint findById(Long id);

    Sprint create(SprintDto sprintDto);

    Sprint update(Long id, SprintDto sprintDto);

    boolean deleteById(Long id);

    boolean isTodayTheEmailDate(Sprint sprint);

    void sendSprintCheckupEmail();

}
