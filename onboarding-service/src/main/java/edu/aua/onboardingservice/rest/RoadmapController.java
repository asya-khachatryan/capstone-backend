package edu.aua.onboardingservice.rest;

import edu.aua.onboardingservice.converter.RoadmapConverter;
import edu.aua.onboardingservice.facade.RoadMapFacade;
import edu.aua.onboardingservice.facade.SprintFacade;
import edu.aua.onboardingservice.service.RoadmapService;
import edu.aua.onboardingservice.service.dto.RoadmapDto;
import edu.aua.onboardingservice.service.dto.SprintDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/roadmap")
public class RoadmapController {
    private final RoadmapService roadmapService;
    private final RoadmapConverter roadmapConverter;
    private final SprintFacade sprintFacade;
    private final RoadMapFacade roadMapFacade;

    public RoadmapController(RoadmapService roadmapService,
                             RoadmapConverter roadmapConverter,
                             SprintFacade sprintFacade,
                             RoadMapFacade roadMapFacade) {
        this.roadmapService = roadmapService;
        this.roadmapConverter = roadmapConverter;
        this.sprintFacade = sprintFacade;
        this.roadMapFacade = roadMapFacade;
    }

    @GetMapping
    public ResponseEntity<List<RoadmapDto>> getAll() {
        return ResponseEntity.ok(roadmapConverter.bulkConvertToDTO(roadmapService.findALl()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoadmapDto> getRoadmapById(@PathVariable Long id) {
        return ResponseEntity.ok(roadmapConverter.convertToDto(roadmapService.findById(id)));
    }

    @PostMapping("/project")
    public ResponseEntity<RoadmapDto> createRoadmap(@RequestBody @Valid RoadmapDto roadmapDTO) {
        return ResponseEntity.ok(roadMapFacade.create(roadmapDTO));
    }

    @PostMapping("/sprint")
    public ResponseEntity<SprintDto> createSprint(@RequestBody @Valid SprintDto sprintDto) {
        return ResponseEntity.ok(sprintFacade.create(sprintDto));
    }
}
