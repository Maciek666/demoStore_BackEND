package pl.time4it.demo_store_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import pl.time4it.demo_store_2.entities.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor {


    String FIND_BY_SERIAL_NO = "select * from product where serial_no = ?1";

    @Query(value = FIND_BY_SERIAL_NO, nativeQuery = true)
    Optional<Product> findBySerialNo(String serialNo);


}
