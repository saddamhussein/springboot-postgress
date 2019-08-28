/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postgresdemo.controller;

import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.repository.ProductRepository;
import com.example.postgresdemo.vo.BaseResponseVo;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author saddam.husein
 */
@RestController
public class ProductController {
    
    @Autowired
    private ProductRepository productRepostiroy;
    
    @PostMapping("/product/create")
    public ResponseEntity<BaseResponseVo<Product>> createProduct(@Valid @RequestBody Product product) {
        productRepostiroy.save(product);
        BaseResponseVo response = new BaseResponseVo(product, String.valueOf(new Date().getTime()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/product/list")
    public ResponseEntity<BaseResponseVo<Page<Product>>> getProducts(Pageable pageable) {
        Page<Product> page = productRepostiroy.findAll(pageable);
        BaseResponseVo<Page<Product>> response = new BaseResponseVo(page, String.valueOf(new Date().getTime()));
        return new ResponseEntity<>( response, HttpStatus.OK);
    }
    
    @GetMapping("/product/listWithStock")
    public ResponseEntity<BaseResponseVo<Page<Product>>> getProductsWithStock(Pageable pageable) {
        Page<Product> page = productRepostiroy.findWithStock(pageable);
        BaseResponseVo<Page<Product>> response = new BaseResponseVo(page, String.valueOf(new Date().getTime()));
        return new ResponseEntity<>( response, HttpStatus.OK);
    }
    
}
