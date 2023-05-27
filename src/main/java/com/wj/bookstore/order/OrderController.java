package com.wj.bookstore.order;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/orders/{orderId}")
    public Mono<ResponseEntity<Order>> getOrderByID(@PathVariable String orderId, Authentication authentication){
        return orderService.getOrderById(orderId, authentication);
    }

    @GetMapping("/orders")
    public ResponseEntity<Flux<Order>> getAllOrders(Authentication authentication){
        return orderService.getAllOrders(authentication);
    }

    @PostMapping("/orders")
    public Mono<ResponseEntity<String>> placeAnOrder(Authentication authentication){
        return orderService.placeOrder(authentication);
    }
}
