package edu.aua.onboardingservice.facade;


import edu.aua.onboardingservice.service.dto.SprintDto;

public interface SprintFacade {

    SprintDto create(SprintDto sprint);

    void delete(Long id);
}
