package com.cg.service.category;

import com.cg.model.Category;
import com.cg.model.dto.CategoryDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICategoryService extends IGeneralService<Category> {
    Category save(Category category);

    void softDelete(Category category);

    Category findByName(String name);

    List<CategoryDTO> findAllCategoryDTO();

    Optional<CategoryDTO> findCategoryDTOById(Long id);
}
