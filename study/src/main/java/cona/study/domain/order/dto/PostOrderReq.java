package cona.study.domain.order.dto;


import lombok.Getter;

import java.util.List;

@Getter
public record PostOrderReq(
        String memberName,
        List<OrderItemList> orderItemList
) {

}
