package cona.study.domain.order.dto;

import lombok.Getter;

@Getter
public record OrderItemList(
        String itemName,
        int quantity
){
}
