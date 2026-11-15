package it.zagato.federico.awesomepizza.repository;

import it.zagato.federico.awesomepizza.entity.Order;

public interface CustomerRepository {

    Order save(Order order);

    Iterable<Order> findAll();
}