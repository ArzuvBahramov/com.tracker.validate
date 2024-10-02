package com.tracker.validate.service;

import com.tracker.validate.dto.ProjectDto;
import com.tracker.validate.dto.TechnologiesDto;
import com.tracker.validate.dto.request.TrackerExperienceRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public abstract class FindMissingTechnology {
    public abstract Map<String, List<String>> getMissingTechnologies(TrackerExperienceRequest request);

    protected boolean isExistsTechnologyInTechnologies(String technology, Set<String> technologies) {
        return technologies.stream().noneMatch(environment -> environment.contains(technology.trim().toLowerCase()));
    }

    protected Set<String> getEnvironments(List<ProjectDto> projects) {
        return projects.stream()
                .map(ProjectDto::environment)
                .flatMap(List::stream)
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

    protected Set<String> getTechnologies(List<TechnologiesDto> technologies) {
        return technologies.stream()
                .map(TechnologiesDto::technologies)
                .flatMap(List::stream)
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }
}

