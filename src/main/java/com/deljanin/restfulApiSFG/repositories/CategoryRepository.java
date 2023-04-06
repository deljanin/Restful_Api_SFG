package com.deljanin.restfulApiSFG.repositories;

import com.deljanin.restfulApiSFG.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
