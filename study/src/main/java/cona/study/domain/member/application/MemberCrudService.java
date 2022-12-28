package cona.study.domain.member.application;

import cona.study.domain.member.domain.Member;
import cona.study.domain.member.dto.GetMembersRes;
import cona.study.domain.member.dto.PatchMemberReq;
import cona.study.domain.member.dto.PostMemberReq;
import cona.study.domain.member.repository.MemberRepository;
import cona.study.global.Error.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCrudService {

    private final MemberRepository memberRepository;

    public List<GetMembersRes> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Member> memberList1 = memberRepository.findAll(pageRequest);

        return memberList1.stream()
                .map(GetMembersRes::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean save(PostMemberReq postMemberReq) { //TODO: 예외처리

        Member member = postMemberReq.toEntity();
        memberRepository.save(member);

        return true;
    }

    public GetMembersRes findById(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);

        return GetMembersRes.of(findMember.orElseThrow(
                () -> new RuntimeException("없는 멤버")
        ));
    }

    public GetMembersRes findByNickname(String nickname) {
        Member member = memberRepository.findByNickname(nickname).orElseThrow(
                () -> new RuntimeException("없는 멤버")
        );

        return GetMembersRes.of(member);
    }

    @Transactional
    public boolean editAccount(Long memberId, PatchMemberReq patchMemberReq) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("없는 맴버")
        );
        if (patchMemberReq.password() != null &&
                !patchMemberReq.password().isBlank()) {
            member.changePassword(patchMemberReq.password());
        }

        if (patchMemberReq.nickname() != null &&
                !patchMemberReq.nickname().isBlank()) {
            member.changeNickName(patchMemberReq.nickname());
        }

        if (patchMemberReq.email() != null &&
                !patchMemberReq.email().isBlank()) {
            member.changeEmail(patchMemberReq.email());
        }

        return true;
    }
}
