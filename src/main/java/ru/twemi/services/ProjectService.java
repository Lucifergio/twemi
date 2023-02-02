package ru.twemi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.twemi.dto.ProjectDto;
import ru.twemi.entity.Project;
import ru.twemi.exception.UserAlreadyExistException;
import ru.twemi.repository.ProjectRepo;
import ru.twemi.services.mapper.ProjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final ProjectMapper projectMapper;

    public List<ProjectDto> findAll() {
        return projectRepo.findAll().stream()
                .map(projectMapper::toProjectDto)
                .collect(Collectors.toList());
    }

    public ProjectDto create(ProjectDto projectDto) {

        if (projectRepo.findByTitleAndDescription(projectDto.getTitle(), projectDto.getDescription()) != null) {
            throw new UserAlreadyExistException();
        }
        Project createdProject = projectMapper.toProject(projectDto);
        return projectMapper.toProjectDto(createdProject);
    }
}
