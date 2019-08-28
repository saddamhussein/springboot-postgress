/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.CartDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author saddam.husein
 */
@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    
    @Query("SELECT cd FROM CartDetail cd WHERE cd.cartId = :cartId and cd.productId = :productId")
    public CartDetail findByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);
    
    public List<CartDetail> findByCartId(Long cartId);
}
