package ru.twemi.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.twemi.dto.security.UserDto;
import ru.twemi.entity.User;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"title", "description", "projectOwner"})
public class ProjectDto {

    @JsonIgnore
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("fundraisingAmount")
    private String fundraisingAmount;

    @JsonProperty("categories")
    private CategoryDto categories;

    @JsonProperty("projectOwner")
    private UserDto projectOwner;

    @JsonProperty("subscribers")
    private Set<UserDto> subscribers;
}
