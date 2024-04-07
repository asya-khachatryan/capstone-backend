package edu.aua.onboardingservice.converter;

import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectRequestDto;
import edu.aua.onboardingservice.persistance.entity.Roadmap;
import edu.aua.onboardingservice.service.dto.RoadmapDto;

import java.util.List;

public interface RoadmapConverter {

    List<RoadmapDto> bulkConvertToDTO(List<Roadmap> roadmaps);

    RoadmapDto convertToDto(Roadmap roadmap);

    ProjectRequestDto convertToJiraProjectDTO(RoadmapDto roadmapDTO);

}
