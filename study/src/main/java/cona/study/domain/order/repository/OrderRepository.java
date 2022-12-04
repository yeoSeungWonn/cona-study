package cona.study.domain.order.repository;

import cona.study.domain.member.domain.Member;
import cona.study.domain.order.domain.Order;
import cona.study.domain.order.domain.OrderStatus;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order save(Order order);


    Page<Order> findByOrderStatus(Pageable pageable, OrderStatus orderStatus);

    Optional<Order> findById(Long id);

    List<Order> findByMember(Member member);
}
