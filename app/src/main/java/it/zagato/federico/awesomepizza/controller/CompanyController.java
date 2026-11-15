package it.zagato.federico.awesomepizza.controller;

import it.zagato.federico.awesomepizza.entity.Order;
import it.zagato.federico.awesomepizza.repository.CustomerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CompanyController.BASE_URL)
public class CompanyController implements CompanyAPI {

    public static final String BASE_URL = "/company";
    public static final String LIST_ORDERS_URL = "/listOrders";

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
}