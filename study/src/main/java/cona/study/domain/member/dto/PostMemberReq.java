package cona.study.domain.member.dto;

import cona.study.domain.member.domain.Address;
import cona.study.domain.member.domain.Member;
import cona.study.domain.member.domain.RoleType;

public record PostMemberReq(
        String name,
        String password,
        String nickname,
        String email,
        Address address,
        RoleType role
) {

    public Member toEntity() {
        return Member.of(
                this.name,
                this.password,
                this.nickname,
                this.email,
                this.address,
                this.role
        );
    }
}
