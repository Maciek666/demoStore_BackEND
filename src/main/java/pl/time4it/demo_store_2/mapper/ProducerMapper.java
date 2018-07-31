package pl.time4it.demo_store_2.mapper;

import pl.time4it.demo_store_2.dtos.ProducerDto;
import pl.time4it.demo_store_2.entities.Producer;
import pl.time4it.demo_store_2.entities.Product;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProducerMapper  implements Mapper<Producer, ProducerDto>{

    @Override
    public ProducerDto map(Producer from) {

        List<String> products = from.getProducts()
                .stream()
                .map(ProductsToString.INSTANCE)
                .collect(Collectors.toList());


        return new ProducerDto(
                from.getName(),
                from.getDescription(),
                products
        );
    }


    private enum ProductsToString implements Function<Product, String> {
        INSTANCE;

        @Override
        public String apply(Product product) {
            return product.getName();
        }
    }


}
