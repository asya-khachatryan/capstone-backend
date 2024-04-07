package edu.aua.onboardingservice.converter.impl;

import edu.aua.onboardingservice.annotation.Converter;
import edu.aua.onboardingservice.client.jiraclient.sprint.dto.JiraSprintDto;
import edu.aua.onboardingservice.converter.SprintConverter;
import edu.aua.onboardingservice.persistance.entity.Sprint;
import edu.aua.onboardingservice.service.dto.SprintDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Converter
public class SprintConverterImpl implements SprintConverter {
    @Override
    public List<JiraSprintDto> bulkConvertToDTO(List<Sprint> sprints) {
        return null;
    }

    @Override
    public SprintDto convertToDto(Sprint sprint) {
        final SprintDto sprintDto = new SprintDto();
        sprintDto.setId(sprint.getId());
        sprintDto.setRoadMapId(sprint.getRoadmap().getId());
        sprintDto.setEndDate(sprint.getEndDate());
        sprintDto.setStartDate(sprint.getStartDate());
        sprintDto.setName(sprint.getName());
        return sprintDto;
    }

    private Date parseDate(String stringDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        try {
            return inputFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JiraSprintDto convertToJiraDto(SprintDto sprintDto) {
        final JiraSprintDto jiraSprintDto = new JiraSprintDto();
        jiraSprintDto.setName(sprintDto.getName());
        jiraSprintDto.setStartDate(sprintDto.getStartDate().toString());
        jiraSprintDto.setEndDate(sprintDto.getEndDate().toString());
        return jiraSprintDto;
    }
}
