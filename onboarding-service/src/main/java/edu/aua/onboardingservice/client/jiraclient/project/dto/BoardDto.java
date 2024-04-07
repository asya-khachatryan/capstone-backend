package edu.aua.onboardingservice.client.jiraclient.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardDto {

    private Long id;
    private String self;
    private String name;
}
