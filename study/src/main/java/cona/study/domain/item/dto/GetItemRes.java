package cona.study.domain.item.dto;

import cona.study.domain.item.domain.Item;

public record GetItemRes (
        String name,
        int price,
        int stockQuantity
){
    public static GetItemRes of(Item item) {
        return new GetItemRes(
                item.getName(),
                item.getPrice(),
                item.getStockQuantity()
        );
    }
}
