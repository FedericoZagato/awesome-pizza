package it.zagato.federico.awesomepizza.controller;

import it.zagato.federico.awesomepizza.entity.Order;
import it.zagato.federico.awesomepizza.entity.OrderStatus;
import it.zagato.federico.awesomepizza.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static it.zagato.federico.awesomepizza.controller.CompanyController.BASE_URL;
import static it.zagato.federico.awesomepizza.controller.CompanyController.LIST_ORDERS_URL;
import static it.zagato.federico.awesomepizza.controller.CompanyController.UPDATE_ORDER_STATUS_URL;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    private static final String LIST_ORDERS_FULL_URL = BASE_URL + LIST_ORDERS_URL;
    private static final String UPDATE_ORDER_STATUS_FULL_URL = BASE_URL + UPDATE_ORDER_STATUS_URL;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderRepository repository;

    @Test
    void testListOrders() throws Exception {
        Order order1 = new Order("margherita");
        order1.setId(1L);
        Order order2 = new Order("prosciutto e funghi");
        order2.setId(2L);
        when(repository.findAll()).thenReturn(List.of(order1, order2));

        mockMvc.perform(get(LIST_ORDERS_FULL_URL))
                .andExpect(status().isOk())
                .andExpect(content().string(allOf(
                        containsString("margherita"),
                        containsString("1"),
                        containsString("prosciutto e funghi"),
                        containsString("2")
                )));
    }

    @Test
    void testUpdateOrderStatus() throws Exception {
        Order order = new Order("");
        order.setStatus(OrderStatus.PREPARING);
        when(repository.findById(1L)).thenReturn(Optional.of(order));
        when(repository.save(any(Order.class))).thenReturn(order);

        mockMvc.perform(get(UPDATE_ORDER_STATUS_FULL_URL)
                        .param("id", "1")
                        .param("status", OrderStatus.PREPARING.name()))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateOrderStatus_OrderNotFound() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get(UPDATE_ORDER_STATUS_FULL_URL)
                        .param("id", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateOrderStatus_NotSaved() throws Exception {
        Order order = new Order("");
        when(repository.findById(1L)).thenReturn(Optional.of(order));
        Order unsavedOrder = new Order("");
        when(repository.save(any(Order.class))).thenReturn(unsavedOrder);

        mockMvc.perform(get(UPDATE_ORDER_STATUS_FULL_URL)
                        .param("id", "1")
                        .param("status", OrderStatus.PREPARING.name()))
                .andExpect(status().isInternalServerError());
    }
}
