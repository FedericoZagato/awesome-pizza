package it.zagato.federico.awesomepizza.dto;

import it.zagato.federico.awesomepizza.entity.OrderStatus;

public record UpdateOrderRequest(Long id, OrderStatus status) {
}