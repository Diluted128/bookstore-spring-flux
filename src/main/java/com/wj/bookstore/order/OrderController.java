package com.wj.bookstore.order;

import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<Order>> getOrderByID(@PathVariable String orderId){
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/orders")
    public ResponseEntity<Flux<Order>> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/orders")
    public Mono<ResponseEntity<String>> placeAnOrder(){
        return orderService.placeOrder();
    }
}
