package com.tracker.validate.service;

import com.tracker.validate.dto.TechnologiesDto;
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
public class FindMissingProjectTechnology extends FindMissingTechnology {
    @Override
    public Map<String, List<String>> getMissingTechnologies(TrackerExperienceRequest request) {
        Map<String, List<String>> missingTechnologiesInProjects;
        Set<String> environments = getEnvironments(request.projects());


        missingTechnologiesInProjects = request.technologies()
                .stream()
                .collect(Collectors.toMap(
                        TechnologiesDto::name,
                        dto ->
                                dto.technologies().stream()
                                        .filter(technology -> isExistsTechnologyInTechnologies(technology, environments))
                                        .collect(Collectors.toList()),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        log.info("{} - technologies is missing in projects", missingTechnologiesInProjects.size());
        return missingTechnologiesInProjects;
    }
}

