package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.ProductDTO;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    private IProductService productService;

    @Autowired
    private AppUtil appUtils;

    @Autowired
    private ICategoryService categoryService;


    @GetMapping
    public ResponseEntity<?> showListProduct() {
        List<ProductDTO> productDTOList = productService.findAllProductDTO();
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            throw new ResourceNotFoundException("Invalid User Id");
        }
        return new ResponseEntity<>(productOptional.get().toProductDTO(), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategory() {

        List<CategoryDTO> categoryDTOS = categoryService.findAllCategoryDTO();

        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        try {

            if (bindingResult.hasErrors()) {
                return appUtils.mapErrorToResponse(bindingResult);
            }
            productDTO.setId(0L);
            Product newProduct = productService.save(productDTO.toProduct());

            return new ResponseEntity<>(newProduct.toProductDTO(), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Thêm sản phẩm thất bại", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

            new ProductDTO().validate(productDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                return appUtils.mapErrorToResponse(bindingResult);
            }

            Product updateProduct = productService.save(productDTO.toProduct());

            return new ResponseEntity<>(updateProduct.toProductDTO(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> doDelete(@PathVariable Long customerId) {

        Optional<Product> optionalProduct = productService.findById(customerId);

        if (optionalProduct.isPresent()) {
            productService.softDelete(optionalProduct.get());
            return new ResponseEntity<>("Delete customer success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error for deleted customer", HttpStatus.BAD_REQUEST);

        }


    }
}
