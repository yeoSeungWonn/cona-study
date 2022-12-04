package cona.study.domain.order.dto;


import java.util.List;

public record PostOrderReq(
        String memberName,
        List<OrderItemList> orderItemList
) {

}
