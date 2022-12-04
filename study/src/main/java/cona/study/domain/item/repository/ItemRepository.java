package cona.study.domain.item.repository;

import cona.study.domain.item.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item save(Item item);

    Page<Item> findAll(Pageable pageable);

    Optional<Item> findById(Long id);

    Optional<Item> findByName(String name);
}
