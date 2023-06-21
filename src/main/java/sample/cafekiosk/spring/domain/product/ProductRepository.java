package sample.cafekiosk.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingTypes);

    @Query(value = "select p.product_number from Product p order by p.id desc limit 1", nativeQuery = true)
    String findLatestProductNumber();
}
