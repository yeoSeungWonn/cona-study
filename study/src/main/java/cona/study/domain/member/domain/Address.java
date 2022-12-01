package cona.study.domain.member.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Address {
    private String city;
    private String street;
    private String detailAddress;

    public static Address of(String city, String street, String detailAddress) {
        return new Address(city, street, detailAddress);
    }

    protected Address() {
    }
}
