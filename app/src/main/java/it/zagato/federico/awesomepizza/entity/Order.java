package it.zagato.federico.awesomepizza.entity;

public class Order {

    private Long id;
    private final String text;
    private OrderStatus status;

    public Order(String text) {
        this.text = text;
        this.status = OrderStatus.CREATED;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean hasId() {
        return id != null;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return Order.class.getSimpleName() + ": " +
                "id=" + id +
                ", text=" + text +
                ", status=" + status;
    }
}
