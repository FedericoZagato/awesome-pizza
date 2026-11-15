package it.zagato.federico.awesomepizza.controller;

import org.springframework.http.ResponseEntity;

interface CompanyAPI {

    ResponseEntity<String> listOrders() ;
}
