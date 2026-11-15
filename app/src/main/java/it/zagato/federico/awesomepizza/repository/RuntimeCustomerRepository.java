package it.zagato.federico.awesomepizza.repository;

import it.zagato.federico.awesomepizza.entity.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
class RuntimeCustomerRepository implements CustomerRepository {

    private final Map<Long, Order> orders;
    private final AtomicLong idGenerator;

    public RuntimeCustomerRepository() {
        this.orders = new HashMap<>();
        this.idGenerator = new AtomicLong(1);
    }

    @Override
    public Order save(Order order) {
        order.setId(idGenerator.getAndIncrement());
        orders.put(order.getId(), order);
        return order;
    }
}