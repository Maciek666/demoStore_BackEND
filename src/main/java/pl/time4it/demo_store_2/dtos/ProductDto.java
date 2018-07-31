package pl.time4it.demo_store_2.dtos;


import lombok.*;
import pl.time4it.demo_store_2.entities.Category;
import pl.time4it.demo_store_2.entities.Producer;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class ProductDto {


    private String name;
    private String description;
    private String categories;
    private BigDecimal price;
    private String producer;
    private Integer quantity;
    private boolean promotion;
    private  String serialNo;
    private String picture;
}
