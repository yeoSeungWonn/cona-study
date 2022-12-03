package cona.study.domain.order.application;

import cona.study.domain.item.domain.Item;
import cona.study.domain.member.domain.Member;
import cona.study.domain.member.domain.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountPolicy discountPolicy;

    public int discount(Member member, Item item) {
        if (member.getRole() == RoleType.VIP) {
            return discountPolicy.discount(item.getPrice());
        } else {
            return 0;
        }
    }
}
