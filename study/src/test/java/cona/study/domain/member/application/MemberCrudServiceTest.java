package cona.study.domain.member.application;

import cona.study.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberCrudServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberCrudService memberCrudService;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void 페이징_테스트() {

        memberCrudService.findAll(2, 3).stream().forEach(System.out::println);
        assertThat(memberCrudService.findAll(2, 3).size()).isEqualTo(0);


    }
}