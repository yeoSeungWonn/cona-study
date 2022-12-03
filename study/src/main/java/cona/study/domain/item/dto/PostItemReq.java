package cona.study.domain.item.dto;

import cona.study.domain.item.domain.Item;

public record PostItemReq(
        String name,
        int price,
        int stockQuantity
) {
    public Item toEntity() {
        return Item.of(
                this.name,
                this.price,
                this.stockQuantity
        );
    }
}
