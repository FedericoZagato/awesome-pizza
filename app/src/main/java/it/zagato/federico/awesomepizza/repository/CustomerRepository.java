package it.zagato.federico.awesomepizza.repository;

import it.zagato.federico.awesomepizza.entity.Order;

import java.util.Optional;

public interface CustomerRepository {

    Order save(Order order);

    Iterable<Order> findAll();

    Optional<Order> findById(Long id);
}