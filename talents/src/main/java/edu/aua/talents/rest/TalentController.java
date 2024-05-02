package edu.aua.talents.rest;

import edu.aua.talents.converter.TalentConverter;
import edu.aua.talents.persistance.entity.Talent;
import edu.aua.talents.service.TalentService;
import edu.aua.talents.service.dto.TalentRequestDTO;
import edu.aua.talents.service.dto.TalentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    //todo convert to dto
    public ResponseEntity<Page<Talent>> getAll(@RequestParam int page,
                                               @RequestParam int size,
                                               @RequestParam String sort) {

        if (sort.isEmpty()) {
            return ResponseEntity.ok(talentService.findAll(PageRequest.of(page, size)));
        } else {
            String[] split = sort.split(",");
            return ResponseEntity.ok(talentService.findAll(PageRequest.of(page, size,
                                    Sort.by(new Sort.Order(Sort.Direction.fromString(split[1].trim()),
                                            split[0].trim()))
                            )
                    )
            );
        }
//                                Sort.by(sort.stream()
//                                        .map(el -> el.split(","))
//                                        .map(ar -> new Sort.Order(Sort.Direction.fromString(ar[1]), ar[0]))
//                                        .toList())


    }

    @GetMapping("/{id}")
    public ResponseEntity<TalentResponseDTO> getTalentById(@PathVariable Long id) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<TalentResponseDTO> create(@RequestBody @Valid TalentRequestDTO talentRequestDTO) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.create(talentRequestDTO)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TalentResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TalentRequestDTO talentRequestDTO) {
        return ResponseEntity.ok(talentConverter.convertToDTO(talentService.update(id, talentRequestDTO)));
    }

    @PutMapping("/update/")
    public ResponseEntity<TalentResponseDTO> updateStatus(@RequestBody TalentRequestDTO dto) {
        return ResponseEntity.ok(talentService.updateStatus(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(talentService.deleteById(id));
    }

    @GetMapping("/specialization/{specializationId}")
    public ResponseEntity<List<TalentResponseDTO>> findBySpecialization(@PathVariable Long specializationId) {
        return ResponseEntity.ok(talentConverter.bulkConvertToDTO(talentService.findBySpecializationId(specializationId)));
    }


    @PostMapping("upload/{talentId}")
    public ResponseEntity<String> uploadAWS(@PathVariable Long talentId, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(talentService.addCVForTalent(talentId, file));
    }

    @GetMapping("/interviewees")
    public ResponseEntity<List<TalentResponseDTO>> interviewees() {
        return ResponseEntity.ok(talentConverter.bulkConvertToDTO(talentService.findInterviewees()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TalentResponseDTO>> search(@RequestParam String query) {
        return ResponseEntity.ok(talentConverter.bulkConvertToDTO(talentService.searchTalent(query)));
    }

    @GetMapping("/cv/{talentId}")
    public ResponseEntity<String> getCvUrl(@PathVariable Long talentId) {
        return ResponseEntity.ok(talentService.getCvUrl(talentId));
    }


}
