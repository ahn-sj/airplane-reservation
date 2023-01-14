package airplainreservation.highestway.member.presentation;

import airplainreservation.highestway.dto.TokenResponse;
import airplainreservation.highestway.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

import static airplainreservation.highestway.dto.MemberRequest.*;


@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> registerMember(@RequestBody MemberRegisterRequest memberRegisterRequest) {
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
}
