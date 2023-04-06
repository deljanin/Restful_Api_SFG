package com.deljanin.restfulApiSFG.api.v1.mapper;

import com.deljanin.restfulApiSFG.api.v1.model.CategoryDTO;
import com.deljanin.restfulApiSFG.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryMapperTest {
    public static final String NAME = "Billy";
    public static final long ID = 1L;
    //    Checking if the Mapper works. This is much cleaner than manually writing the mappers.

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() throws Exception{
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }

}