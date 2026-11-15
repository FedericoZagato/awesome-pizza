package it.zagato.federico.awesomepizza.repository;

import it.zagato.federico.awesomepizza.entity.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
        if (!order.hasId()) {
            order.setId(idGenerator.getAndIncrement());
        }
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Iterable<Order> findAll() {
        return orders.values();
    }

    @Override
    public Optional<Order> findById(Long id) {
        if (orders.containsKey(id)) {
            return Optional.of(orders.get(id));
        }
        return Optional.empty();
    }
}