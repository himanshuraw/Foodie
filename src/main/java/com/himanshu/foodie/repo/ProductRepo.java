package com.himanshu.foodie.repo;

import com.himanshu.foodie.entity.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @NonNull
    Optional<Product> findById(@NonNull Long id);

    @Query(value = "select * from product " +
            "where price between :minPrice and :maxPrice " +
            "order by price desc  limit 2",
            nativeQuery = true)
    List<Product> query(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

}
