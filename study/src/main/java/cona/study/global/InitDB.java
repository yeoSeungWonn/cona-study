package cona.study.global;

import cona.study.domain.item.domain.Item;
import cona.study.domain.member.domain.Address;
import cona.study.domain.member.domain.Member;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static cona.study.domain.member.domain.RoleType.USER;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {

            Address addressA = Address.of("Seoul", "streetA", "101");
            Member member1 = Member.of("memberA", "aaaa", "memberA", "memberA@gmail.com", addressA, USER);
            em.persist(member1);

            Address addressB = Address.of("Seoul", "streetB", "101");
            Member member2 = Member.of("memberB", "bbbb", "memberB", "memberB@gmail.com", addressB, USER);
            em.persist(member2);

            Address addressC = Address.of("Seoul", "streetA", "101");
            Member member3 = Member.of("memberC", "cccc", "memberC", "memberC@gmail.com", addressC, USER);
            em.persist(member3);

            Address address = Address.of("Seoul", "street", "101");


            for (int i = 0; i < 100; i++) {
                Member member = Member.of("member" + i,  Integer.toString(i), "member" + i, "member" + i + "@gmail.com", address, USER);
                em.persist(member);
            }

            Item itemA = Item.of("itemA", 10000, 100);
            em.persist(itemA);

            Item itemB = Item.of("itemB", 12000, 120);
            em.persist(itemB);

        }
    }
}
