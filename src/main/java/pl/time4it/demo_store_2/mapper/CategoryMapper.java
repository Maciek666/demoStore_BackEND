package pl.time4it.demo_store_2.mapper;

import pl.time4it.demo_store_2.dtos.CategoryDto;
import pl.time4it.demo_store_2.entities.Category;

public class CategoryMapper implements Mapper<Category, CategoryDto>{


    @Override
    public CategoryDto map(Category from) {
        return new CategoryDto(
                from.getName(),
                from.getDescription()
        );
    }
}
