package cona.study.domain.order.repository;

import cona.study.domain.item.domain.Item;
import cona.study.domain.member.domain.Address;
import cona.study.domain.member.domain.Member;
import cona.study.domain.member.domain.RoleType;
import cona.study.domain.member.repository.MemberRepository;
import cona.study.domain.order.domain.Order;
import cona.study.domain.order.domain.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 주문_저장소_맴버_이름으로_주문_조회() {
        Address address = Address.of("asfds", "sdfsdf", "sdfdsd");
        Member member = Member.of("a", "1", "ds", "dfs", address, RoleType.USER);
        memberRepository.save(member);


        Item item = Item.of("sdf", 100, 2);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(OrderItem.of(item, 0, 1));

        Order order = Order.of(member, orderItemList);
        orderRepository.save(order);

        List<Order> byMember = orderRepository.findByMember(member);
        assertThat(order).isEqualTo(byMember.get(0));

    }

}