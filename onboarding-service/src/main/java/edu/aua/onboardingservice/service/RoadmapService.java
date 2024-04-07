package edu.aua.onboardingservice.service;

import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectResponseDto;
import edu.aua.onboardingservice.exception.RoadmapNotFoundException;
import edu.aua.onboardingservice.persistance.entity.Roadmap;
import edu.aua.onboardingservice.service.dto.RoadmapDto;

import java.util.List;

public interface RoadmapService {

    List<Roadmap> findALl();

    Roadmap findById(Long id) throws RoadmapNotFoundException;

    Roadmap create(RoadmapDto roadmapDTO, ProjectResponseDto jiraProject);

    Roadmap update(Long id, RoadmapDto roadmapDTO) throws RoadmapNotFoundException;

    boolean deleteById(Long id);

    Roadmap updateStatus(Long id, String status);

    Roadmap findByProjectKey(String projectKey);
}
