package com.deljanin.restfulApiSFG.services;

import com.deljanin.restfulApiSFG.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
