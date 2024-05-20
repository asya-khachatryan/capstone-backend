package edu.aua.common.rest;

import edu.aua.common.converter.SpecializationConverter;
import edu.aua.common.model.SpecializationDTO;
import edu.aua.common.service.SpecializationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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

import java.util.List;

@RestController
@RequestMapping("/specialization")
@CrossOrigin
public class SpecializationController {

    private final SpecializationService service;
    private final SpecializationConverter converter;

    public SpecializationController(SpecializationService service, SpecializationConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<SpecializationDTO>> getAll() {
        return ResponseEntity.ok(converter.bulkConvertToDTO(service.findALl()));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SpecializationDTO>> getAll(@RequestParam int page,
                                                          @RequestParam int size,
                                                          @RequestParam String sort) {
        if (sort == null || sort.isEmpty()) {
            return ResponseEntity.ok(service.findAll(PageRequest.of(page, size)));
        } else {
            String[] split = sort.split(",");
            return ResponseEntity.ok(service.findAll(PageRequest.of(page, size,
                                    Sort.by(new Sort.Order(Sort.Direction.fromString(split[1].trim()),
                                            split[0].trim()))
                            )
                    )
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecializationDTO> getSpecializationById(@PathVariable Long id) {
        return ResponseEntity.ok(converter.convertToDTO(service.findByIdOrThrow(id)));
    }

    @PostMapping
    public ResponseEntity<SpecializationDTO> create(@RequestBody @Valid SpecializationDTO specializationRequestDTO) {
        return ResponseEntity.ok(converter.convertToDTO(service.create(specializationRequestDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecializationDTO> update(@PathVariable Long id, @RequestBody @Valid SpecializationDTO specializationRequestDTO) {
        return ResponseEntity.ok(converter.convertToDTO(service.update(id, specializationRequestDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
