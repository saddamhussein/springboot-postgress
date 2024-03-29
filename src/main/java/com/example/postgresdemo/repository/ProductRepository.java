package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author saddam.husein
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    @Query(value = "SELECT p FROM Product p WHERE p.stock > 0")
    Page<Product> findWithStock(Pageable pageable);
}
