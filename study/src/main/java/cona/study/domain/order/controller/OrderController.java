package cona.study.domain.order.controller;

import cona.study.domain.order.application.OrderCrudService;
import cona.study.domain.order.dto.GetOrderRes;
import cona.study.domain.order.dto.PostOrderReq;
import cona.study.domain.order.repository.OrderRepository;
import cona.study.global.ApiDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderCrudService orderCrudService;

    @PostMapping("")
    public ApiDataResponse<String> makeOrder(
            @RequestBody PostOrderReq postOrderReq
            ) {
        boolean result = orderCrudService.makeOrders(postOrderReq);
        return ApiDataResponse.of(Boolean.toString(result));
    }

    @GetMapping("")
    public ApiDataResponse<List<GetOrderRes>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ApiDataResponse.of(orderCrudService.findAll(page, size));
    }

    @PostMapping("/{id}")
    public ApiDataResponse<String> cancelOrder(@PathVariable("id") Long id) {
        boolean result = orderCrudService.cancelOrder(id);
        return ApiDataResponse.of(Boolean.toString(result));
    }

    @GetMapping("/cancel")
    public ApiDataResponse<List<GetOrderRes>> findAllCancelledOrder(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ApiDataResponse.of(orderCrudService.findAllCancelOrder(page, size));

    }
}
