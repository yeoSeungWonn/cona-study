package cona.study.domain.order.dto;

import cona.study.domain.member.domain.Address;
import cona.study.domain.order.domain.Order;
import cona.study.domain.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record GetOrderRes(
        String memberName,
        Address address,
        List<OrderItemDto> orderItems,
        int originalPrice,
        int discountPrice,
        int totalPrice,
        LocalDateTime orderAt,
        OrderStatus orderStatus

) {
    public static GetOrderRes of(Order order) {
        return new GetOrderRes(
                order.getMember().getName(),
                order.getMember().getAddress(),
                order.getOrderItems()
                        .stream()
                        .map(OrderItemDto::of)
                        .collect(Collectors.toList()),
                order.getTotalPrice() + order.getTotalDiscount(),
                order.getTotalPrice(),
                order.getTotalDiscount(),
                order.getOrderDate(),
                order.getOrderStatus()
        );
    }
}
