package cona.study.domain.item.controller;


import cona.study.domain.item.application.ItemCrudService;
import cona.study.domain.item.dto.GetItemRes;
import cona.study.domain.item.dto.PostItemReq;
import cona.study.global.ApiDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemCrudService itemCrudService;

    @PostMapping("")
    public ApiDataResponse<String> saveItem(@RequestBody PostItemReq postItemReq) {
        boolean result = itemCrudService.save(postItemReq);
        return ApiDataResponse.of(Boolean.toString(result));
    }

    @GetMapping("")
    public ApiDataResponse<List<GetItemRes>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "criteria", defaultValue = "createdAt") String criteria) {
        return ApiDataResponse.of(itemCrudService.findAll(page, size, criteria));
    }
}
