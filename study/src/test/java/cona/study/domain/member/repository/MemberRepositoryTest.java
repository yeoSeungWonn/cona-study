package cona.study.domain.member.repository;

import cona.study.domain.member.domain.Address;
import cona.study.domain.member.domain.Member;
import cona.study.domain.member.domain.RoleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원저장소_테스트() {

        Member member = Member.of("a",
                "1",
                "a",
                "e@gmail.com",
                Address.of("a", "b", "c")
                , RoleType.USER);

        Member savedMember = memberRepository.save(member);

        Optional<Member> findMemberId = memberRepository.findById(savedMember.getId());

        assertThat(savedMember).isEqualTo(findMemberId
                .orElseThrow());
    }

    @Test
    void 닉네임으로_찾기() {
        Member member = Member.of("a",
                "1",
                "a",
                "e@gmail.com",
                Address.of("a", "b", "c")
                , RoleType.USER);

       Member savedMember = memberRepository.save(member);

        Optional<Member> byNick = memberRepository.findByNickname("a");

        assertThat(byNick.orElseThrow()).isEqualTo(savedMember);
    }
}