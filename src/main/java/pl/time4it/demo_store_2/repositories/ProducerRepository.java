package pl.time4it.demo_store_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.time4it.demo_store_2.entities.Category;
import pl.time4it.demo_store_2.entities.Producer;

import javax.transaction.Transactional;
import java.util.Optional;


@Transactional
@Repository //informacja dla spriga o repozytorium
public interface ProducerRepository extends JpaRepository<Producer, Long>,
        JpaSpecificationExecutor {



    String FIND_BY_TITLE = "select * from producer where name = ?1";

    @Query(value = FIND_BY_TITLE, nativeQuery = true)
    Optional<Producer> findByTitle(String title);

}
