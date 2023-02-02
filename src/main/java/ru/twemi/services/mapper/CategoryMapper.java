package ru.twemi.services.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import ru.twemi.dto.CategoryDto;
import ru.twemi.entity.Category;

@Mapper
public interface CategoryMapper {

    Category toCategory (CategoryDto categoryDto);
    CategoryDto toCategoryDto (Category category);
}
