package cona.study.domain.order.application;

import cona.study.domain.item.domain.Item;
import cona.study.domain.item.repository.ItemRepository;
import cona.study.domain.member.domain.Member;
import cona.study.domain.member.repository.MemberRepository;
import cona.study.domain.order.domain.Order;
import cona.study.domain.order.domain.OrderItem;
import cona.study.domain.order.domain.OrderStatus;
import cona.study.domain.order.dto.GetOrderRes;
import cona.study.domain.order.dto.OrderItemList;
import cona.study.domain.order.dto.PostOrderReq;
import cona.study.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderCrudService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final DiscountService discountService;

    @Transactional
    public Boolean makeOrders(PostOrderReq postOrderReq) {
        Member member = memberRepository.findByNickname(postOrderReq.memberName()).orElseThrow(
                () -> new RuntimeException("없는 회원입니다")
        );

        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItemList orderItem : postOrderReq.orderItemList()) {
            Item item = itemRepository.findByName(orderItem.itemName())
                    .orElseThrow(
                            () -> new RuntimeException("없는 상품입니다.")
                    );
            orderItemList.add(OrderItem.of(item,
                    discountService.discount(member, item),
                    orderItem.quantity()));
        }

        Order order = Order.of(member, orderItemList);
        orderRepository.save(order);
        return true;
    }

    public List<GetOrderRes> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,
                size,
                Sort.by(Sort.Direction.DESC, "orderDate"));
        Page<Order> orderList = orderRepository.findByOrderStatus(pageRequest, OrderStatus.ORDER);

        return orderList.stream()
                .map(GetOrderRes::of)
                .collect(Collectors.toList());
    }

    public List<GetOrderRes> findAllCancelOrder(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,
                size,
                Sort.by(Sort.Direction.ASC, "modifiedDate"));
        Page<Order> orderList = orderRepository.findByOrderStatus(pageRequest, OrderStatus.CANCEL);

        return orderList.stream()
                .map(GetOrderRes::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("없는 주문입니다."));
        if (order.getOrderStatus() == OrderStatus.CANCEL) {
            throw new RuntimeException("이미 취소된 주문입니다.");
        }
        order.cancel();
        return true;
    }
}
