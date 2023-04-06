package com.deljanin.restfulApiSFG.api.v1.mapper;

import com.deljanin.restfulApiSFG.api.v1.model.CategoryDTO;
import com.deljanin.restfulApiSFG.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
//    @Mapping(source = "id", target = "id") Use this when some properties are different.
    CategoryDTO categoryToCategoryDTO(Category category);
}
