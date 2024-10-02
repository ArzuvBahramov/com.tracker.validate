package com.tracker.validate.service;

import com.tracker.validate.dto.ProjectDto;
import com.tracker.validate.dto.TechnologiesDto;
import com.tracker.validate.dto.request.TrackerExperienceRequest;
import com.tracker.validate.dto.response.MissingTechnologyResponse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class MissingTechnologyServiceTest {

    @Inject
    MissingTechnologyService missingTechnologyService;
    private TechnologiesDto technologies;
    private ProjectDto projectDto;
    private TrackerExperienceRequest request;
    @BeforeEach
    public void setupTechnologies() {
        technologies = TechnologiesDto.builder()
                .name("Programming languages")
                .technologies(Arrays
                        .stream("Java, Java 8,Java 11, Java 17, Python, JavaScript, TypeScript"
                                .split(","))
                        .toList())
                .build();

        projectDto = ProjectDto.builder()
                .name("INSURANCE COMPANY SYSTEM")
                .description("Project for one of the biggest European insurance companies. It is a big Enterprise application for internal employees from different countries that gives them efficient tools to work with clients and various documents. We had a big remote team from anywhere and worked together with QA’s, DevOps’es, and support.\n")
                .role("Software Engineer")
                .period("12.2018 – 02.2021")
                .environment(Arrays.stream(
                        "Java 8, JavaScript, TypeScript, MySQL, FlyWay, Docker-Compose, Docker, NodeJS, Angular, Redux, Jest, HTML, CSS,TestContainers, JUnit, Mockito, Jenkins, Maven, Lombok, Swagger, Git, GitLab, Trello".split(", ")
                ).toList())
                .build();

        request = TrackerExperienceRequest.builder()
                .projects(List.of(projectDto))
                .technologies(List.of(technologies))
                .build();
    }
    @Test
    public void checkMissingTechnologies() {
        MissingTechnologyResponse response = missingTechnologyService.checkMissingTechnologies(request);

        assertEquals(3, response.getMissingTechnologiesInProjects().get("Programming languages").size(), "3 technology just shouldn't exists in projects.");
        assertEquals("Java 11", response.getMissingTechnologiesInProjects().get("Programming languages").getFirst());
        assertEquals(19, response.getMissingTechnologiesInHeader().get("INSURANCE COMPANY SYSTEM").size());
    }
}

