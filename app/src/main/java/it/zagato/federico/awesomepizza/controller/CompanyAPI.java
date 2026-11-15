package it.zagato.federico.awesomepizza.controller;

import it.zagato.federico.awesomepizza.dto.UpdateOrderRequest;

import org.springframework.http.ResponseEntity;

interface CompanyAPI {

    ResponseEntity<String> listOrders() ;

    ResponseEntity<Void> updateOrderStatus(UpdateOrderRequest request) ;
}
