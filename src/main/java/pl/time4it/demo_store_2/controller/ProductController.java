package pl.time4it.demo_store_2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.time4it.demo_store_2.dtos.ProductDto;
import pl.time4it.demo_store_2.entities.Category;
import pl.time4it.demo_store_2.entities.Producer;
import pl.time4it.demo_store_2.entities.Product;
import pl.time4it.demo_store_2.mapper.ProductMapper;
import pl.time4it.demo_store_2.repositories.CategoryRepository;
import pl.time4it.demo_store_2.repositories.ProducerRepository;
import pl.time4it.demo_store_2.repositories.ProductRepository;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping("products")
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        ProductMapper mapper = new ProductMapper();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p : products) {
            ProductDto productDto = mapper.map(p);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @RequestMapping(value = "products/{serialNo}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> delete(@PathVariable String serialNo) {
        Optional<Product> optionalProduct = productRepository.findBySerialNo(serialNo);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(optionalProduct.get().getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //    private String name;
//    private String description;
//    private String categories;
//    private BigDecimal price;
//    private String producer;
//    private Integer quantity;
//    private boolean promotion;
//    private  String serialNo;
//    private String picture;


    @RequestMapping(value = "products/update", method = RequestMethod.PUT)
    public void update(@RequestBody ProductDto productDto) {
        update(productDto.getSerialNo(),productDto.getName(), productDto.getDescription(), productDto.getPrice().toString(),
               productDto.getQuantity(), productDto.isPromotion(), productDto.getPicture());

    }


        @RequestMapping(value = "products/add/post", method = RequestMethod.POST)
    public void add(@RequestBody ProductDto productDto) {

        add(productDto.getName(), productDto.getDescription(), productDto.getCategories(), productDto.getPrice().toString(),
                productDto.getProducer(), productDto.getQuantity(), productDto.isPromotion(), productDto.getSerialNo(), productDto.getPicture());

    }


    @RequestMapping(value = "products/add", method = RequestMethod.POST)
    public ResponseEntity<Product> add(

            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "category") String category,
            @RequestParam(value = "price") String price,
            @RequestParam(value = "producer") String producer,
            @RequestParam(value = "quantity") Integer quantity,
            @RequestParam(value = "promotion") boolean promotion,
            @RequestParam(value = "serialNo") String serialNo,
            @RequestParam(value = "picture", required = false) String picture) {

        Optional<Product> optionalProduct = productRepository.findBySerialNo(serialNo);
        Optional<Category> optionalCategory = categoryRepository.findByTitle(category);
        Optional<Producer> optionalProducer = producerRepository.findByTitle(producer);

        if (!optionalProduct.isPresent()
                && optionalCategory.isPresent()
                && optionalProducer.isPresent()) {

            Product product = new Product();

            product.setName(name);
            product.setDescription(description);
            product.setCategory(optionalCategory.get());
            product.setPrice(new BigDecimal(price));
            product.setProducer(optionalProducer.get());
            product.setQuantity(quantity);
            product.setPromotion(promotion);
            product.setSerialNo(serialNo);
            product.setPicture(picture);

            productRepository.save(product);

            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @RequestMapping(value = "products/update/{serialNo}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(
            @PathVariable String serialNo,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price") String price,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "promotion", required = false) boolean promotion,
            @RequestParam(value = "picture", required = false) String picture
    ) {
        Optional<Product> optionalProduct = productRepository.findBySerialNo(serialNo);

        if (!optionalProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Product product = optionalProduct.get();

            product.setId(optionalProduct.get().getId());
            product.setName(name);
            product.setDescription(description);

            product.setPrice(new BigDecimal(price));
            product.setQuantity(quantity);
            product.setPromotion(promotion);
            product.setSerialNo(serialNo);
            product.setPicture(picture);


            productRepository.save(product);

            return ResponseEntity.ok().build();
        }

    }


    @GetMapping("products/{serialNo}")
    public ProductDto getProduct(@PathVariable String serialNo) {
        Optional<Product> optionalProduct = productRepository.findBySerialNo(serialNo);

        if (optionalProduct.isPresent()) {
            //  Optional<Product> result = productRepository.findById(optionalProduct.get().getId())

            ProductMapper mapper = new ProductMapper();
            return mapper.map(optionalProduct.get());
        }

        return new ProductDto();

    }
}
