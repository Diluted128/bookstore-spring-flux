package com.wj.bookstore.order;

import com.wj.bookstore.cart.CartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartManager cartManager;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        CartManager cartManager){
        this.orderRepository = orderRepository;
        this.cartManager = cartManager;
    }

    public ResponseEntity<Flux<Order>> getAllOrders(Authentication authentication){
        Flux<Order> orders = orderRepository.findOrderByUsername(authentication.getName());
        return ResponseEntity.accepted().body(orders);
    }

    public Mono<ResponseEntity<Order>> getOrderById(String orderId, Authentication authentication) {
        return orderRepository.findOrdersByUsernameAndOrderId(authentication.getName(), orderId)
                .flatMap(order -> Mono.just(ResponseEntity.ok(order)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    public Mono<ResponseEntity<String>> placeOrder(Authentication authentication) {
        return Mono.defer(() -> {
            if (cartManager.getItems().isEmpty()) {
                return Mono.just(ResponseEntity.badRequest().body("Shopping cart is empty"));
            }

            Order order = new Order(
                    null,
                    authentication.getName(),
                    cartManager.getItems(),
                    LocalDate.now()
            );

            return orderRepository.save(order)
                    .then(Mono.fromRunnable(cartManager::clear))
                    .thenReturn(ResponseEntity.ok("Order placed successfully"));
        });
    }
}
