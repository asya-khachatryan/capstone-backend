package edu.aua.auth.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ErrorCollectorImpl implements ErrorCollector {

    private final ObjectMapper objectMapper;

    @Override
    public Collection<String> collectDefaultMessages(ValidationError error) {
        return error.getViolations()
                .stream()
                .map(Violation::getMessage)
                .collect(Collectors.toList());
    }

    @Override
    public String defaultMessagesAsJson(ValidationError error) {
        Map<String, Object> map = new HashMap<>();

        map.put("errorMessages", collectDefaultMessages(error));
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

}
