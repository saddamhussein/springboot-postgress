package com.example.postgresdemo.controller;

import com.example.postgresdemo.biz.OrderBiz;
import com.example.postgresdemo.model.Order;
import com.example.postgresdemo.util.HttpStatusUtil;
import com.example.postgresdemo.vo.BaseResponseVo;
import com.example.postgresdemo.vo.CheckoutVo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderController {
    
    @Autowired
    private OrderBiz orderBiz;
    
    @PostMapping(value = "/checkout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseVo<Order>> checkout(@Valid @RequestBody CheckoutVo vo) throws Exception {
        
        BaseResponseVo<Order> response = orderBiz.checkoutOrder(vo);
        return new ResponseEntity<>(response, HttpStatusUtil.getHttpStatusByCode(response.getCode()));
    }
    
}
