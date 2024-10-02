package com.tracker.validate.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
public class MissingTechnologyResponse {
    Map<String, List<String>> missingTechnologiesInHeader;
    Map<String, List<String>> missingTechnologiesInProjects;
}
