/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postgresdemo.biz;

import com.example.postgresdemo.enumeration.OrderStatus;
import com.example.postgresdemo.model.Cart;
import com.example.postgresdemo.model.CartDetail;
import com.example.postgresdemo.model.Order;
import com.example.postgresdemo.model.OrderDetail;
import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.rabbitmq.OrderMessageSender;
import com.example.postgresdemo.repository.CartDetailRepository;
import com.example.postgresdemo.repository.CartRepository;
import com.example.postgresdemo.repository.OrderDetailRepository;
import com.example.postgresdemo.repository.OrderRepository;
import com.example.postgresdemo.repository.ProductRepository;
import com.example.postgresdemo.vo.BaseResponseVo;
import com.example.postgresdemo.vo.CheckoutVo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author saddam.husein
 */
@Component
public class OrderBiz {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrderMessageSender orderMessageSender;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartDetailRepository cartDetailRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrderDetailRepository orderDetailRepo;

    public BaseResponseVo<Order> checkoutOrder(CheckoutVo checkoutVo) {
        String requestId = String.valueOf(new Date().getTime());
        Cart cart = cartRepo.findById(checkoutVo.getCartId()).orElse(null);
        if (cart == null) {
            return new BaseResponseVo(9, "Invalid cartId", requestId);
        }
        BaseResponseVo response = new BaseResponseVo(requestId);
        Order order = new Order();
        order.setPaymentType(checkoutVo.getPaymentType());
        order.setShippingFee(BigDecimal.ONE);
        order.setUsername(cart.getUsername());
        order = orderRepo.save(order);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<CartDetail> cartDetails = cartDetailRepo.findByCartId(cart.getId());

        for (CartDetail cartDetail : cartDetails) {
            Product product = productRepo.findById(cartDetail.getProductId()).orElse(null);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setProductId(product.getId());
            orderDetail.setQuantity(cartDetail.getQuantity());
            orderDetail.setProductDescription(product.getDescription());
            orderDetail.setProductName(product.getName());
            orderDetail.setProductPrice(product.getPrice());
            totalAmount = totalAmount.add(product.getPrice().multiply(new BigDecimal(cartDetail.getQuantity())));
            orderDetailRepo.save(orderDetail);
            cartDetailRepo.delete(cartDetail);
        }
        order.setTotalProductAmount(totalAmount);
        order.setStatus(OrderStatus.PLACED.name());
        order = orderRepo.save(order);
        cartRepo.delete(cart);
        orderMessageSender.sendOrder(order);
        response.setEntity(order);
        return response;
    }

}
