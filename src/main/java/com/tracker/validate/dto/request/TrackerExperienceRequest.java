package com.tracker.validate.dto.request;

import com.tracker.validate.dto.ProjectDto;
import com.tracker.validate.dto.TechnologiesDto;
import lombok.Builder;

import java.util.List;

@Builder
public record TrackerExperienceRequest(
        List<TechnologiesDto> technologies,
        List<ProjectDto> projects
){

}