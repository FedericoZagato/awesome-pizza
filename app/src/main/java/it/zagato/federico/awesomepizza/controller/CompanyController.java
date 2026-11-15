package it.zagato.federico.awesomepizza.controller;

import it.zagato.federico.awesomepizza.dto.UpdateOrderRequest;
import it.zagato.federico.awesomepizza.entity.Order;
import it.zagato.federico.awesomepizza.repository.CustomerRepository;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CompanyController.BASE_URL)
public class CompanyController implements CompanyAPI {

    public static final String BASE_URL = "/company";
    public static final String LIST_ORDERS_URL = "/listOrders";
    public static final String UPDATE_ORDER_STATUS_URL = "/updateOrderStatus";

    private final CustomerRepository repository;

    public CompanyController(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    @GetMapping(LIST_ORDERS_URL)
    public ResponseEntity<String> listOrders() {
        Iterable<Order> orders = repository.findAll();

        StringBuilder builder = new StringBuilder();
        for (Order order: orders) {
            builder.append("Order " + order.getId() + ", status " + order.getStatus() + ", text " + order.getText() + "<br/>");
        }
        return ResponseEntity.ok().body(builder.toString());
    }

    @Override
    @GetMapping(UPDATE_ORDER_STATUS_URL)
    public ResponseEntity<Void> updateOrderStatus(UpdateOrderRequest request) {
        Optional<Order> optional = repository.findById(request.id());
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Order order = optional.get();
        order.setStatus(request.status());
        Order saved = repository.save(order);
        if (saved.getStatus().equals(request.status())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}