package cona.study.domain.order.repository;

import cona.study.domain.member.domain.Member;
import cona.study.domain.order.domain.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order save(Order order);

    @Override
    List<Order> findAll();

    Optional<Order> findById(Long id);

    List<Order> findByMember(Member member);
}
