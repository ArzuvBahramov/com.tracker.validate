package com.tracker.validate;

import com.tracker.validate.dto.ProjectDto;
import com.tracker.validate.dto.TechnologiesDto;
import com.tracker.validate.dto.request.TrackerExperienceRequest;
import com.tracker.validate.service.MissingTechnologyService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class MissingTechnologyControllerTest {
    private TrackerExperienceRequest request;
    @BeforeEach
    public void setupTechnologies() {
        TechnologiesDto technologies = TechnologiesDto.builder()
                .name("Programming languages")
                .technologies(Arrays
                        .stream("Java, Java 8,Java 11, Java 17, Python, JavaScript, TypeScript"
                                .split(","))
                        .toList())
                .build();

        ProjectDto projectDto = ProjectDto.builder()
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
    void checkMissingTechnologies() {
        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                    .post("/v1/missing-technologies")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

}