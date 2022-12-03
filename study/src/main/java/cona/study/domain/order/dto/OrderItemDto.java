package cona.study.domain.order.dto;

import cona.study.domain.order.domain.OrderItem;

public record OrderItemDto(
        String itemName,
        int itemPrice,
        int discount,
        int orderPrice,
        int count
) {
    public static OrderItemDto of(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getItem().getName(),
                orderItem.getItem().getPrice(),
                orderItem.getOrderPrice(),
                orderItem.getDiscount(),
                orderItem.getCount()
        );
    }
}
