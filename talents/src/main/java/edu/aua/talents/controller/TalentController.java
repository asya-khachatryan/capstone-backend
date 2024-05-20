package edu.aua.talents.controller;

import edu.aua.common.exception.NotFoundException;
import edu.aua.talents.converter.TalentConverter;
import edu.aua.talents.persistance.dto.TalentRequestDTO;
import edu.aua.talents.persistance.dto.TalentResponseDTO;
import edu.aua.talents.service.TalentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/talent")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class TalentController {

    private final TalentService talentService;
    private final TalentConverter talentConverter;

    public TalentController(TalentService talentService, TalentConverter talentConverter) {
        this.talentService = talentService;
        this.talentConverter = talentConverter;
    }

    @GetMapping
    public ResponseEntity<List<TalentResponseDTO>> getAll() {
        return ResponseEntity.ok(talentConverter.bulkConvertToDTO(talentService.findAll()));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<TalentResponseDTO>> getAll(@RequestParam int page,
                                                          @RequestParam int size,
                                                          @RequestParam String sort) {
        if (sort == null || sort.isEmpty()) {
            return ResponseEntity.ok(talentService.findAllAppliedAndRejected(PageRequest.of(page, size)));
        } else {
            String[] split = sort.split(",");
            return ResponseEntity.ok(talentService.findAllAppliedAndRejected(PageRequest.of(page, size,
                                    Sort.by(new Sort.Order(Sort.Direction.fromString(split[1].trim()),
                                            split[0].trim()))
                            )
                    )
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TalentResponseDTO> getTalentById(@PathVariable Long id) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.findByIdOrThrow(id)));
    }

    @GetMapping("/test")
    public void test() {
        throw new RuntimeException("Test exception");
    }

    @GetMapping("/test1")
    public void test1() {
        throw new NotFoundException(String.format("Test exception %s", 1));
    }

    @PostMapping
    public ResponseEntity<TalentResponseDTO> create(@RequestBody @Valid TalentRequestDTO talentRequestDTO) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.create(talentRequestDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TalentResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TalentRequestDTO talentRequestDTO) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.update(id, talentRequestDTO)));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<TalentResponseDTO> updateStatus(@PathVariable Long id,
                                                          @RequestBody TalentRequestDTO dto,
                                                          @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(talentService.updateStatus(id, userDetails.getUsername(), dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(talentService.deleteById(id));
    }

    @GetMapping("/specialization/{specializationId}")
    public ResponseEntity<List<TalentResponseDTO>> findBySpecialization(@PathVariable Long specializationId) {
        return ResponseEntity.ok(talentConverter.bulkConvertToDTO(talentService.findBySpecializationId(specializationId)));
    }


    @PostMapping("/upload/{talentId}")
    public ResponseEntity<String> uploadAWS(@PathVariable Long talentId, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(talentService.addCVForTalent(talentId, file));
    }

    @GetMapping("/interviewees")
    public ResponseEntity<List<TalentResponseDTO>> interviewees() {
        return ResponseEntity.ok(talentConverter.bulkConvertToDTO(talentService.findInterviewees()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TalentResponseDTO>> search(@RequestParam String query, @RequestParam String type) {
        return ResponseEntity.ok(talentConverter.bulkConvertToDTO(talentService.searchTalent(query, type)));
    }

    @GetMapping("/cv/{talentId}")
    public ResponseEntity<String> getCvUrl(@PathVariable Long talentId) {
        return ResponseEntity.ok(talentService.getCvUrl(talentId));
    }


}
