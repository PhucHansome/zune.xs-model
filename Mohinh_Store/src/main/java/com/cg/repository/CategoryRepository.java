package com.cg.repository;

import com.cg.model.Category;
import com.cg.model.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);


    @Query("SELECT new com.cg.model.dto.CategoryDTO (" +
            "c.id, " +
            "c.name " +
            ") " +
            "FROM Category AS c"
    )
    List<CategoryDTO> findAllCategoryDTO();

    @Query("SELECT new com.cg.model.dto.CategoryDTO (" +
            "c.id, " +
            "c.name " +
            ") " +
            "FROM Category AS c WHERE c.id = ?1"
    )
    Optional<CategoryDTO>findCategoryDTOById(Long id);

}
