package pl.time4it.demo_store_2.mapper;

import lombok.*;
import pl.time4it.demo_store_2.dtos.ProductDto;
import pl.time4it.demo_store_2.entities.Product;

import java.math.BigDecimal;


public class ProductMapper implements Mapper<Product, ProductDto> {


//    private String name;
//    private String description;
//    private String categories;
//    private BigDecimal price;
//    private String producer;
//    private Integer quantity;
//    private boolean promotion;
//    private  String serialNo;
//    private String picture;

    @Override
    public ProductDto map(Product from) {


        return new ProductDto(
                from.getName(),
                from.getDescription(),
                from.getCategory().getName(),
                from.getPrice(),
                from.getProducer().getName(),
                from.getQuantity(),
                from.isPromotion(),
                from.getSerialNo(),
                from.getPicture()
        );
    }
}
