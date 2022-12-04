package cona.study.domain.member.dto;

public record PatchMemberReq(
        String password,
        String nickname,
        String email
) {
}
