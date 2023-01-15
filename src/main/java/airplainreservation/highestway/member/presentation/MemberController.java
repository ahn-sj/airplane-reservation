package airplainreservation.highestway.member.presentation;

import airplainreservation.highestway.common.response.TokenResponse;
import airplainreservation.highestway.member.application.MemberService;
import airplainreservation.highestway.common.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

import static airplainreservation.highestway.common.request.MemberRequest.MemberLoginRequest;
import static airplainreservation.highestway.common.request.MemberRequest.MemberRegisterRequest;
import static airplainreservation.highestway.common.response.MemberResponse.MemberFindResponse;


@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> registerMember(@RequestBody @Valid MemberRegisterRequest memberRegisterRequest) {
        memberRegisterRequest.updateEncodedPassword(passwordEncoder.encode(memberRegisterRequest.getPassword()));
        Long memberId = memberService.registerMember(memberRegisterRequest.toEntity());

        return ResponseEntity.created(URI.create("/api/member/" + memberId)).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody MemberLoginRequest memberLoginRequest,
                                               HttpServletResponse httpServletResponse) {
        TokenResponse tokenResponse = memberService.login(memberLoginRequest, httpServletResponse);

        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberFindResponse> findMember(@PathVariable Long memberId,
                                                         @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(memberService.find(memberId));

    }
}
