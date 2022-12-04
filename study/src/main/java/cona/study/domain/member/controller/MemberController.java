package cona.study.domain.member.controller;

import cona.study.domain.member.application.MemberCrudService;
import cona.study.domain.member.dto.GetMembersRes;
import cona.study.domain.member.dto.PatchMemberReq;
import cona.study.domain.member.dto.PostMemberReq;
import cona.study.global.ApiDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCrudService memberCrudService;

    @PostMapping("")
    public ApiDataResponse<String> saveMember(
            @RequestBody PostMemberReq postMemberReq
            ) {
        boolean result = memberCrudService.save(postMemberReq);
        return ApiDataResponse.of(Boolean.toString(result));
    }

    @GetMapping("")
    public ApiDataResponse<List<GetMembersRes>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ApiDataResponse.of(memberCrudService.findAll(page, size));
    }

    @GetMapping("/{nickname}")
    public ApiDataResponse<GetMembersRes> findMemberByNickname(
            @PathVariable("nickname") String nickname) {
        return ApiDataResponse.of(memberCrudService.findByNickname(nickname));
    }

    @PatchMapping("/{id}")
    public ApiDataResponse<String> updateMemberInfo(@PathVariable("id") Long id,
                                                    @RequestBody PatchMemberReq patchMemberReq) {

        boolean result = memberCrudService.editAccount(id, patchMemberReq);
        return ApiDataResponse.of(Boolean.toString(result));
    }
}
