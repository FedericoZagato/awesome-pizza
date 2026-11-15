package it.zagato.federico.awesomepizza.controller;

import it.zagato.federico.awesomepizza.dto.CreateOrderRequest;
import it.zagato.federico.awesomepizza.dto.GetOrderStatusRequest;

import org.springframework.http.ResponseEntity;

interface CustomerAPI {

    ResponseEntity<Long> createOrder(CreateOrderRequest request) ;

    ResponseEntity<String> getOrderStatus(GetOrderStatusRequest request);
}
