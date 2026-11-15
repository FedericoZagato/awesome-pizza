package it.zagato.federico.awesomepizza.controller;

import it.zagato.federico.awesomepizza.entity.Order;
import it.zagato.federico.awesomepizza.repository.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static it.zagato.federico.awesomepizza.controller.CustomerController.BASE_URL;
import static it.zagato.federico.awesomepizza.controller.CustomerController.CREATE_ORDER_URL;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    private static final String CREATE_ORDER_FULL_URL = BASE_URL + CREATE_ORDER_URL;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerRepository repository;

    @Test
    void testCreateOrder_Saved() throws Exception {
        Order order = new Order("margherita");
        order.setId(1L);
        when(repository.save(any(Order.class))).thenReturn(order);

        mockMvc.perform(get(CREATE_ORDER_FULL_URL).param("text", "margherita"))
                .andExpect(status().isAccepted())
                .andExpect(content().string("1"));
    }

    @Test
    void createOrder_ShouldReturnBadRequest_WhenOrderHasNoId() throws Exception {
        Order order = new Order("margherita");
        when(repository.save(any(Order.class))).thenReturn(order);

        mockMvc.perform(get(CREATE_ORDER_FULL_URL).param("text", "margherita"))
                .andExpect(status().isBadRequest());
    }
}
