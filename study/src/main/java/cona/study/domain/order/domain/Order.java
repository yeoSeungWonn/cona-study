package cona.study.domain.order.domain;

import cona.study.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime orderDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedDate;

    public static Order of(Member member, List<OrderItem> orderItems) {
        Order order = new Order(member);

        for (OrderItem orderItem : orderItems) {
            order.setOrderItem(orderItem);
        }
        return order;
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
