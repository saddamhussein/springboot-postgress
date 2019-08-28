/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postgresdemo.biz;

import com.example.postgresdemo.model.Cart;
import com.example.postgresdemo.model.CartDetail;
import com.example.postgresdemo.model.Member;
import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.repository.CartDetailRepository;
import com.example.postgresdemo.repository.CartRepository;
import com.example.postgresdemo.repository.MemberRepository;
import com.example.postgresdemo.repository.ProductRepository;
import com.example.postgresdemo.vo.BaseResponseVo;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author saddam.husein
 */
@Component
public class CartBiz {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private MemberRepository memberRepository;

    public BaseResponseVo<Cart> addToCart(Map<String, Object> payload) {
        String requestId = String.valueOf(new Date().getTime());
        String username = (String) payload.get("username");
        Member member = memberRepository.findByUsername(username);
        if(member == null)
            return new BaseResponseVo(8, "username invalid", requestId);
        Integer quantity = Integer.valueOf(payload.get("quantity").toString());
        Long productId = Long.valueOf(payload.get("productId").toString());
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return new BaseResponseVo(7, "Product invalid", requestId);
        }else if (product != null && product.getStock() < quantity) {
            return new BaseResponseVo(6, "Stock Unvailable", requestId);
        }
        Cart cart = cartRepository.findByUsername(username);
        CartDetail cartDetail = null;
        if (cart == null) {
            cart = new Cart();
            cart.setUsername(username);
            cart = cartRepository.save(cart);
        } else {
            cartDetail = cartDetailRepository.findByCartIdAndProductId(cart.getId(), productId);
        }
        if (cartDetail == null) {
            cartDetail = new CartDetail();
            cartDetail.setQuantity(quantity);
            cartDetail.setProductId(productId);
            cartDetail.setCartId(cart.getId());
        } else {
            cartDetail.setQuantity(quantity + cartDetail.getQuantity());
        }
        cartDetail = cartDetailRepository.save(cartDetail);
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
        return new BaseResponseVo(cart, requestId);

    }
}