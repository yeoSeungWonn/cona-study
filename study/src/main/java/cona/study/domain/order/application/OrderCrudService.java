package cona.study.domain.order.application;

import cona.study.domain.item.domain.Item;
import cona.study.domain.item.repository.ItemRepository;
import cona.study.domain.member.domain.Member;
import cona.study.domain.member.repository.MemberRepository;
import cona.study.domain.order.domain.Order;
import cona.study.domain.order.domain.OrderItem;
import cona.study.domain.order.dto.GetOrderRes;
import cona.study.domain.order.dto.OrderItemList;
import cona.study.domain.order.dto.PostOrderReq;
import cona.study.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
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
public class OrderCrudService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final DiscountService discountService;

    @Transactional
    public Boolean makeOrders(PostOrderReq postOrderReq) {
        Member member = memberRepository.findByNickname(postOrderReq.getMemberName()).orElseThrow(
                () -> new RuntimeException("없는 회원입니다")
        );

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemList orderItemList : postOrderReq.getOrderItemList()) {
            Item item = itemRepository.findByName(orderItemList.getItemName())
                    .orElseThrow(
                            () -> new RuntimeException("없는 상품입니다.")
                    );
            orderItems.add(OrderItem.of(item,
                    discountService.discount(member, item),
                    orderItemList.getQuantity()));
        }

        Order order = Order.of(member, orderItems);
        orderRepository.save(order);
        return true;
    }

    public List<GetOrderRes> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,
                size,
                Sort.by(Sort.Direction.DESC, "orderDate"));
        Page<Order> orderList = orderRepository.findAll(pageRequest);

        return orderList.stream()
                .map(GetOrderRes::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("없는 주문입니다."));
        order.cancel();
        return true;
    }


}
