package com.tracker.validate.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TechnologiesDto(
        String name,
        List<String> technologies
) {
}