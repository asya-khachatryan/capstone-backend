package edu.aua.onboardingservice.service;

import edu.aua.onboardingservice.client.jiraclient.user.dto.JiraUserDto;
import edu.aua.onboardingservice.persistance.entity.Mentee;
import edu.aua.onboardingservice.service.dto.MenteeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenteeService {

    List<Mentee> findALl();

    Mentee findById(Long id);

    Mentee create(MenteeDto menteeDTO, JiraUserDto jirUser);

    Mentee update(Long id, MenteeDto menteeDTO);

    boolean deleteById(Long id);

    boolean sendOnboardingEmail(Long menteeId, String hrManagerUsername, String documentUrl);
}
