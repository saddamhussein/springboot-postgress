/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postgresdemo.controller;

import com.example.postgresdemo.biz.CartBiz;
import com.example.postgresdemo.model.Cart;
import com.example.postgresdemo.model.CartDetail;
import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.repository.CartDetailRepository;
import com.example.postgresdemo.repository.CartRepository;
import com.example.postgresdemo.repository.ProductRepository;
import com.example.postgresdemo.util.HttpStatusUtil;
import com.example.postgresdemo.vo.BaseResponseVo;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author saddam.husein
 */
@RestController
public class CartController {
    
    @Autowired
    private CartBiz cartBiz;
    
    @PostMapping(value = "/cart/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseVo<Cart>> addToCart( @RequestBody Map<String, Object> payload ) throws Exception{
        BaseResponseVo<Cart> response = cartBiz.addToCart(payload);
        return new ResponseEntity<>(response, HttpStatusUtil.getHttpStatusByCode(response.getCode()));
    }
}