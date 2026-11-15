package it.zagato.federico.awesomepizza.controller;

import it.zagato.federico.awesomepizza.dto.CreateOrderRequest;
import it.zagato.federico.awesomepizza.dto.GetOrderStatusRequest;
import it.zagato.federico.awesomepizza.entity.Order;
import it.zagato.federico.awesomepizza.repository.CustomerRepository;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController implements CustomerAPI {

    public static final String BASE_URL = "/customer";
    public static final String CREATE_ORDER_URL = "/createOrder";
    public static final String GET_ORDER_STATUS_URL = "/getOrderStatus";

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    @GetMapping(CREATE_ORDER_URL)
    public ResponseEntity<Long> createOrder(CreateOrderRequest request) {
        Order order = new Order(request.text());
        Order saved = repository.save(order);
        if (saved.hasId()) {
            return ResponseEntity.accepted().body(saved.getId());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @GetMapping(GET_ORDER_STATUS_URL)
    public ResponseEntity<String> getOrderStatus(GetOrderStatusRequest request) {
        Optional<Order> optional = repository.findById(request.id());
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(optional.get().getStatus().name());
    }
}