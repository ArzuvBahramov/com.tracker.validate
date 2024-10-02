package com.tracker.validate.service;

import com.tracker.validate.dto.request.TrackerExperienceRequest;
import com.tracker.validate.dto.response.MissingTechnologyResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@ApplicationScoped
public class MissingTechnologyService {

    @Inject
    FindMissingProjectTechnology findMissingProjectTechnology;

    @Inject
    FindMissingHeaderTechnology findMissingHeaderTechnology;

    public MissingTechnologyResponse checkMissingTechnologies(TrackerExperienceRequest trackerExperience) {
        Map<String, List<String>> missingTechnologiesInProjects = findMissingProjectTechnology.getMissingTechnologies(trackerExperience);
        Map<String, List<String>> missingTechnologiesInHeader = findMissingHeaderTechnology.getMissingTechnologies(trackerExperience);

        log.info("Missing technologies is checked");
        return MissingTechnologyResponse.builder()
                .missingTechnologiesInHeader(missingTechnologiesInHeader)
                .missingTechnologiesInProjects(missingTechnologiesInProjects)
                .build();
    }
}
