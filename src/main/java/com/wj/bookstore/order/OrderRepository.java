package com.wj.bookstore.order;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
    Flux<Order> findOrderByUsername(String username);

    Mono<Order> findOrdersByUsernameAndOrderId(String username, String orderId);
}
