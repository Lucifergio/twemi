package ru.twemi.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.twemi.dto.ProjectDto;
import ru.twemi.dto.security.UserDto;
import ru.twemi.services.ProjectService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectDto> list() {
        return projectService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody ProjectDto projectDto) {
        ProjectDto createdProjectDto = projectService.create(projectDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/project/" + createdProjectDto.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
}
