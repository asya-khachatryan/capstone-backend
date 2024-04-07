package edu.aua.talents.rest;

import edu.aua.talents.converter.SpecializationConverter;
import edu.aua.talents.service.SpecializationService;
import edu.aua.talents.service.dto.SpecializationRequestDTO;
import edu.aua.talents.service.dto.SpecializationResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specialization")
@CrossOrigin
public class SpecializationController {

    private final SpecializationService specializationService;
    private final SpecializationConverter specializationConverter;

    public SpecializationController(SpecializationService specializationService, SpecializationConverter specializationConverter) {
        this.specializationService = specializationService;
        this.specializationConverter = specializationConverter;
    }

    @GetMapping
    public ResponseEntity<List<SpecializationResponseDTO>> getAll() {
        return ResponseEntity.ok(specializationConverter.bulkConvertToDTO(specializationService.findALl()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecializationResponseDTO> getSpecializationById(@PathVariable Long id) {
        return ResponseEntity.ok(specializationConverter.convertToDTO(specializationService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<SpecializationResponseDTO> create(@RequestBody @Valid SpecializationRequestDTO specializationRequestDTO) {
        return ResponseEntity.ok(specializationConverter.convertToDTO(specializationService.create(specializationRequestDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecializationResponseDTO> update(@PathVariable Long id, @RequestBody @Valid SpecializationRequestDTO specializationRequestDTO) {
        return ResponseEntity.ok(specializationConverter.convertToDTO(specializationService.update(id, specializationRequestDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(specializationService.deleteById(id));
    }
}
