package it.zagato.federico.awesomepizza.repository;

import it.zagato.federico.awesomepizza.entity.Order;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Primary;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Primary
public class RuntimeOrderRepositoryTest {

    private RuntimeOrderRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new RuntimeOrderRepository();
    }

    @Test
    public void testSave() {
        Order order1 = new Order("order1");
        order1 = repository.save(order1);
        assertEquals(1, order1.getId());

        Order order2 = new Order("order2");
        order2 = repository.save(order2);
        assertEquals(2, order2.getId());

        Order order3 = new Order("order3");
        order3.setId(5L);
        order3 = repository.save(order3);
        assertEquals(5, order3.getId());
    }

    @Test
    public void testFindById() {
        Order order = new Order("order1");
        repository.save(order);

        Optional<Order> optional = repository.findById(2L);
        assertEquals(Optional.empty(), optional);

        optional = repository.findById(1L);
        assertEquals(Optional.of(order), optional);
    }
}
