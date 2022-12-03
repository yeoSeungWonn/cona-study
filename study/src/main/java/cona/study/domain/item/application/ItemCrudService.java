package cona.study.domain.item.application;

import cona.study.domain.item.domain.Item;
import cona.study.domain.item.dto.GetItemRes;
import cona.study.domain.item.dto.PostItemReq;
import cona.study.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemCrudService {

    private final ItemRepository itemRepository;

    public List<GetItemRes> findAll(int page, int size, String orderBy) {

        Sort.Direction direction = Sort.Direction.DESC;
        if (orderBy.equals("price")) {
            direction = Sort.Direction.ASC;
        }

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, orderBy));
        Page<Item> itemList = itemRepository.findAll(pageRequest);

        return itemList.stream()
                .map(GetItemRes::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean save(PostItemReq postItemReq) {
        Item item = postItemReq.toEntity();
        itemRepository.save(item);

        return true;
    }

    public GetItemRes findById(Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);

        return GetItemRes.of(findItem.orElseThrow(
                () -> new RuntimeException("없는 아이템")
        ));
    }
}
