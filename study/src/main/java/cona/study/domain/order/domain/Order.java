package cona.study.domain.order.domain;

import cona.study.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime orderDate;

    public static Order of(Member member, List<OrderItem> orderItems) {
        Order orders = new Order(member);

        for (OrderItem orderItem : orderItems) {
            orders.setOrderItem(orderItem);
        }
        return orders;
    }

    public void cancel() {
        this.setOrderStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getOrderPrice() * orderItem.getCount();
        }
        return totalPrice;
    }

    public int getTotalDiscount() {
        int totalDiscount = 0;
        for (OrderItem orderItem : orderItems) {
            totalDiscount += orderItem.getDiscount() * orderItem.getCount();
        }
        return totalDiscount;
    }

    protected Order() {
    }

    private Order(Member member) {
        this.member = member;
        this.orderStatus = OrderStatus.ORDER;
    }

    private void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    private void setOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.addOrderToOrderItem(this);
    }

}
