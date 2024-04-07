package edu.aua.onboardingservice.facade.impl;

import edu.aua.onboardingservice.annotation.Facade;
import edu.aua.onboardingservice.client.jiraclient.JiraIntegrationClientFacade;
import edu.aua.onboardingservice.client.jiraclient.enums.RoleType;
import edu.aua.onboardingservice.client.jiraclient.project.dto.AssignUserProjectRoleDto;
import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectRequestDto;
import edu.aua.onboardingservice.client.jiraclient.project.dto.ProjectResponseDto;
import edu.aua.onboardingservice.converter.RoadmapConverter;
import edu.aua.onboardingservice.facade.RoadMapFacade;
import edu.aua.onboardingservice.persistance.entity.Roadmap;
import edu.aua.onboardingservice.persistance.enums.RoadmapStatus;
import edu.aua.onboardingservice.service.MenteeService;
import edu.aua.onboardingservice.service.RoadmapService;
import edu.aua.onboardingservice.service.dto.RoadmapDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Facade
public class RoadMapFacadeImpl implements RoadMapFacade {

    private final static Logger log = LoggerFactory.getLogger(RoadMapFacadeImpl.class);

    private final JiraIntegrationClientFacade jiraClientFacade;
    private final RoadmapConverter roadmapConverter;
    private final RoadmapService roadmapService;

    public RoadMapFacadeImpl(final JiraIntegrationClientFacade jiraClientFacade,
                             final RoadmapConverter roadmapConverter,
                             final RoadmapService roadmapService) {
        this.jiraClientFacade = jiraClientFacade;
        this.roadmapConverter = roadmapConverter;
        this.roadmapService = roadmapService;
    }

    @Override
    public RoadmapDto create(final RoadmapDto roadmapDto) {
        log.info("In create roadMap");
        final ProjectRequestDto projectRequestDto = roadmapConverter.convertToJiraProjectDTO(roadmapDto);
        final ProjectResponseDto projectResponseDto = jiraClientFacade.createProject(projectRequestDto);
        final Roadmap roadmap = roadmapService.create(roadmapDto, projectResponseDto);
        roadmap.setStatus(RoadmapStatus.STARTED);

        final AssignUserProjectRoleDto userProjectRole = new AssignUserProjectRoleDto();
        userProjectRole.setRole(RoleType.MEMBER);
        userProjectRole.setUser(List.of(roadmap.getMentee().getAccountId()));

        this.jiraClientFacade.addUserToProject(roadmap.getJiraProjectKey(), userProjectRole);
        return roadmapConverter.convertToDto(roadmap);
    }

    @Override
    public boolean delete(final Long id) {
        return this.roadmapService.deleteById(id);
    }

    @Override
    public RoadmapDto getById(final Long id) {
        return this.roadmapConverter.convertToDto(this.roadmapService.findById(id));
    }

    @Override
    public List<RoadmapDto> getAll() {
        return this.roadmapConverter.bulkConvertToDTO(this.roadmapService.findALl());
    }

    @Override
    public RoadmapDto update(final Long id, final RoadmapDto roadmapDto) {
        return this.roadmapConverter.convertToDto(this.roadmapService.update(id, roadmapDto));
    }
}
