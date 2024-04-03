package ma.dante.ioc2.repository;

import ma.dante.ioc2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContains(String mc);

    List<Product> findByNameContaining(String keyword);
}
