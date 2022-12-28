package cona.study.domain.order.domain;

import cona.study.domain.item.domain.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Entity
@Slf4j
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;

    private int count;
    private int discount;
    private int orderPrice;

    public static OrderItem of(Item item, int discount, int count) {
        item.removeStock(count);
        return new OrderItem(item, discount, count);
    }

    public void cancel() {
        getItem().addStock(count);
    }

    public void addOrderToOrderItem(Order order) {
        this.order = order;
    }

    protected OrderItem() {
    }

    private OrderItem(Item item, int discount, int count) {
        this.item = item;
        this.count = count;
        this.discount = discount;
        this.orderPrice = item.getPrice() - discount;
    }
}
