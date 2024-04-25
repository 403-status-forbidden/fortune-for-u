package com.a403.ffu.member.controller;

import com.a403.ffu.global.security.LoginUser;
import com.a403.ffu.member.dto.MemberDetailsResponse;
import com.a403.ffu.member.dto.MemberInfoResponse;
import com.a403.ffu.counselorform.dto.CounselorFormRequest;
import com.a403.ffu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    // Member 정보 조회
    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponse> memberInfo(@AuthenticationPrincipal LoginUser loginUser) {
        log.trace("loginUser: {}", loginUser.getName());

        return new ResponseEntity<>(
                memberService.getMemberInfo(loginUser.getMember()),
                HttpStatus.OK
        );
    }

    // MyPage에서 보여줄 Member 정보 조회 (followers 추가)
    @GetMapping("/details")
    public ResponseEntity<MemberDetailsResponse> memberDetails(@AuthenticationPrincipal LoginUser loginUser) {

        return new ResponseEntity<>(
                memberService.getMemberDetails(loginUser.getMember()),
                HttpStatus.OK
        );
    }

    @GetMapping("/logout")
    public void logout() {

        // TODO: 로그아웃 -> refresh token 삭제
    }

    @PutMapping("follow/{followeeId}")
    public HttpStatus follow(@PathVariable Long followeeId, @AuthenticationPrincipal LoginUser loginUser) {

        memberService.follow(loginUser.getMember(), memberService.findById(followeeId));
        return HttpStatus.NO_CONTENT;
    }

    @DeleteMapping("unfollow/{followeeId}")
    public HttpStatus unfollow(@PathVariable Long followeeId, @AuthenticationPrincipal LoginUser loginUser) {

        memberService.unfollow(loginUser.getMember(), memberService.findById(followeeId));
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("isfollowing/{followeeId}")
    public Boolean isFollowing(@PathVariable Long followeeId, @AuthenticationPrincipal LoginUser loginUser) {

        return memberService.isFollowing(loginUser.getMember(), memberService.findById(followeeId));
    }

    @PostMapping("/submit")
    public HttpStatus submitCounselorForm(
            @RequestBody CounselorFormRequest counselorFormRequest,
            @AuthenticationPrincipal LoginUser loginUser){

        memberService.submitCounselorForm(counselorFormRequest, loginUser.getMember());
        return HttpStatus.CREATED;
    }

    @PutMapping(value = "/profileImage", consumes = {"multipart/form-data"})
    public HttpStatus updateProfileImage(@AuthenticationPrincipal LoginUser loginUser,
                                         @RequestPart("image") MultipartFile profileImageFile) throws IOException {

        memberService.updateProfileImage(loginUser.getMember().getNo(), profileImageFile);
        return HttpStatus.OK;
    }
}
