package com.cg.model.dto;


import com.cg.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CategoryDTO {


    @Min(1)
    @Max(7)
    private Long id;

    private String name;

    public Category toCategory(){
        return new Category()
                .setId(id)
                .setName(name);
    }
}
