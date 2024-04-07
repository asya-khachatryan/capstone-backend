package edu.aua.onboardingservice.facade;

import edu.aua.onboardingservice.service.dto.RoadmapDto;

import java.util.List;

public interface RoadMapFacade {

    RoadmapDto create(RoadmapDto roadmapDto);

    boolean delete(Long id);

    RoadmapDto getById(Long id);

    List<RoadmapDto> getAll();

    RoadmapDto update(Long id, RoadmapDto roadmapDto);

}
