package cona.study.domain.member.repository;

import cona.study.domain.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends
        JpaRepository<Member, Long>,
        MemberRepositoryCustom {

    Member save(Member member);

    Page<Member> findAll(Pageable pageable);

    Optional<Member> findById(Long id);

    Optional<Member> findByNickname(String nickName);

}
