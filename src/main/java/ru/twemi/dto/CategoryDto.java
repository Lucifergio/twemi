package ru.twemi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"title"})
public class CategoryDto {

    @JsonIgnore
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("projects")
    private Set<ProjectDto> projects;
}
