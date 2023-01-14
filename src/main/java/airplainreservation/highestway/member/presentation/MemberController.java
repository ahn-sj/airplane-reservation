package airplainreservation.highestway.member.presentation;

import airplainreservation.highestway.dto.MemberRequest;
import airplainreservation.highestway.member.application.MemberService;
import airplainreservation.highestway.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static airplainreservation.highestway.dto.MemberRequest.*;


@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String registerMember(@RequestBody MemberRegisterRequest memberRegisterRequest) {
        memberService.registerMember(memberRegisterRequest);

        return "loginForm";
    }
}
