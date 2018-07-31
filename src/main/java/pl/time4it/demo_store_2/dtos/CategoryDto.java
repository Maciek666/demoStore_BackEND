package pl.time4it.demo_store_2.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private String name;
    private String description;
  //  private List<String> products;
}
