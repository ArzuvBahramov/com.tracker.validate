package com.tracker.validate.service;

import com.tracker.validate.dto.ProjectDto;
import com.tracker.validate.dto.request.TrackerExperienceRequest;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class FindMissingHeaderTechnology extends FindMissingTechnology {
    @Override
    public Map<String, List<String>> getMissingTechnologies(TrackerExperienceRequest request) {
        Map<String, List<String>> missingTechnologiesInHeader = new LinkedHashMap<>();
        Set<String> technologies = getTechnologies(request.technologies());

        missingTechnologiesInHeader = request.projects()
                .stream()
                .collect(Collectors.toMap(
                        ProjectDto::name,
                        dto ->
                                dto.environment().stream()
                                        .filter(technology -> isExistsTechnologyInTechnologies(technology, technologies))
                                        .collect(Collectors.toList()),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        log.info("{} - technologies is missing in headers", missingTechnologiesInHeader.size());
        return missingTechnologiesInHeader;
    }
}

