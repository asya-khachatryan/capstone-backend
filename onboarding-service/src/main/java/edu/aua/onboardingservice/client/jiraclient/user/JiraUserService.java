package edu.aua.onboardingservice.client.jiraclient.user;

import edu.aua.onboardingservice.client.jiraclient.user.dto.JiraUserDto;

public interface JiraUserService {

    JiraUserDto createUser(JiraUserDto user);

    void deleteUser(String accountId);

}
