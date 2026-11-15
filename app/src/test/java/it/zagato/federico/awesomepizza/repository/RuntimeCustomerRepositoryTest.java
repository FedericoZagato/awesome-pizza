package it.zagato.federico.awesomepizza.repository;

import it.zagato.federico.awesomepizza.entity.Order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Primary;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Primary
public class RuntimeCustomerRepositoryTest {

    private RuntimeCustomerRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new RuntimeCustomerRepository();
    }

    @Test
    public void testSave() {
        Order order1 = new Order("order1");
        order1 = repository.save(order1);
        assertEquals(1, order1.getId());

        Order order2 = new Order("order2");
        order2 = repository.save(order2);
        assertEquals(2, order2.getId());
    }
}
