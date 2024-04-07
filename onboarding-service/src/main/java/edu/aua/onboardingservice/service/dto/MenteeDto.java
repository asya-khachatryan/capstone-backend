package edu.aua.onboardingservice.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenteeDto extends UserDto {

    private Long mentorId;

    private Long roadmapId;
}
