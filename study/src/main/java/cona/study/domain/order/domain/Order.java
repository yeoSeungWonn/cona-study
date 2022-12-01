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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING) private OrderStatus orderStatus;

    @CreatedDate
    @Column(nullable = false) private LocalDateTime orderDate;




}
