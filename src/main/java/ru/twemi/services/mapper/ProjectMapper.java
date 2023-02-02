package ru.twemi.services.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import ru.twemi.dto.ProjectDto;
import ru.twemi.entity.Project;

@Mapper
@Primary
public interface ProjectMapper{

    Project toProject (ProjectDto projectDto);
    ProjectDto toProjectDto (Project project);
}
