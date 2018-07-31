package pl.time4it.demo_store_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import pl.time4it.demo_store_2.dtos.OpinionDto;
import pl.time4it.demo_store_2.entities.Opinion;
import pl.time4it.demo_store_2.entities.Product;

import java.util.List;
import java.util.Optional;

public interface OpinionRespository extends JpaRepository<Opinion, Long>,
        JpaSpecificationExecutor {
    String FIND_OPINIONS = "select * from opinion where serial_no = ?1";

    @Query(value = FIND_OPINIONS, nativeQuery = true)
    List<Opinion> findOpinionsByProduct(String serialNo);


}
