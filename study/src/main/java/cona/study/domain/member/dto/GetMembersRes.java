package cona.study.domain.member.dto;

import cona.study.domain.member.domain.Address;
import cona.study.domain.member.domain.Member;
import cona.study.domain.member.domain.RoleType;

public record GetMembersRes(
        String name,
        String nickname,
        String email,
        Address address,
        RoleType role
) {
    public static GetMembersRes of(
            Member member
    ) {
        return new GetMembersRes(
                member.getName(),
                member.getNickname(),
                member.getEmail(),
                member.getAddress(),
                member.getRole()
        );
    }

}