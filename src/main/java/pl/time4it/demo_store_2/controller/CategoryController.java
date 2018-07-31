package pl.time4it.demo_store_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.time4it.demo_store_2.dtos.CategoryDto;
import pl.time4it.demo_store_2.entities.Category;
import pl.time4it.demo_store_2.mapper.CategoryMapper;
import pl.time4it.demo_store_2.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("categories")
    public List<CategoryDto> getCategories(
            @RequestParam(value = "expression", required = false) String expression) {

        List<CategoryDto> categoriesDtos = new ArrayList<>();

        if (expression == null) {

            List<Category> categories = categoryRepository.findAll();
            CategoryMapper mapper = new CategoryMapper();
            for (Category c : categories) {
                CategoryDto categoryDto = mapper.map(c);
                categoriesDtos.add(categoryDto);
            }

        } else {
            List<Category> categories = categoryRepository.findByExpression(expression);
            CategoryMapper mapper = new CategoryMapper();
            for (Category c : categories) {
                CategoryDto categoryDto = mapper.map(c);
                categoriesDtos.add(categoryDto);
            }

        }

        return categoriesDtos;
    }


}
