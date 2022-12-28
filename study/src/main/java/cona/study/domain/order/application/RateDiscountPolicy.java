package cona.study.domain.order.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10;

    @Override
    public int discount(int price) {
        return price * discountPercent / 100;
    }
}
