package com.tracker.validate.controller;

import com.tracker.validate.dto.request.TrackerExperienceRequest;
import com.tracker.validate.dto.response.MissingTechnologyResponse;
import com.tracker.validate.service.MissingTechnologyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/v1/missing-technologies")
@Slf4j
public class MissingTechnologyController {
    @Inject
    MissingTechnologyService missingTechnologyService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkMissingTechnologies(TrackerExperienceRequest request) {
        log.info("Post request checkMissingTechnologies is called!");
        MissingTechnologyResponse response = missingTechnologyService.checkMissingTechnologies(request);
        return Response.ok(response).build();
    }
}