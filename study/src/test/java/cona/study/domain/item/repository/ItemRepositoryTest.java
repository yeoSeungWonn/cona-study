package cona.study.domain.item.repository;

import cona.study.domain.item.application.ItemCrudService;
import cona.study.domain.item.dto.GetItemRes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemCrudService itemCrudService;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void item_정렬() {

        List<GetItemRes> updatedAt = itemCrudService.findAll(0, 10,"price");
        updatedAt.stream().forEach(System.out::println);

    }
}