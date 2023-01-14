package airplainreservation.highestway.member.application;

import airplainreservation.highestway.dto.TokenResponse;
import airplainreservation.highestway.exception.CustomCommonException;
import airplainreservation.highestway.exception.ErrorCode;
import airplainreservation.highestway.member.domain.Member;
import airplainreservation.highestway.member.infrastructure.MemberRepository;
import airplainreservation.highestway.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

import static airplainreservation.highestway.dto.MemberRequest.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public Long registerMember(Member member) {
        if(memberRepository.existsByEmail(member.getEmail())) {
            throw new CustomCommonException(ErrorCode.DUPLICATE_EMAIL);
        }

        if(memberRepository.existsByUsername(member.getUsername())) {
            throw new CustomCommonException(ErrorCode.DUPLICATE_USERNAME);
        }

        Member saveMember = memberRepository.save(member);

        return saveMember.getId();
    }

    @Transactional
    public TokenResponse login(MemberLoginRequest memberLoginRequest, HttpServletResponse httpServletResponse) {
        Member member = findMemberByUsername(memberLoginRequest);
        checkPassword(memberLoginRequest, member);

        TokenResponse tokenResponse = tokenProvider.createAllToken(member.getUsername());

        addTokenHeader(httpServletResponse, tokenResponse);

        return tokenResponse;
    }

    private void addTokenHeader(HttpServletResponse response, TokenResponse tokenResponse) {
        response.addHeader(TokenProvider.ACCESS_TOKEN, tokenResponse.getAccessToken());
//        response.addHeader(TokenProvider.REFRESH_TOKEN, tokenResponse.getRefreshToken());
    }

    private Member findMemberByUsername(MemberLoginRequest memberLoginRequest) {
        return memberRepository.findByUsername(memberLoginRequest.getUsername()).orElseThrow(
                () -> new CustomCommonException(ErrorCode.NOT_EXISTS_MEMBER)
        );
    }

    private void checkPassword(MemberLoginRequest memberLoginRequest, Member member) {
        if(!passwordEncoder.matches(memberLoginRequest.getPassword(), member.getPassword())){
            throw new CustomCommonException(ErrorCode.NOT_EQUAL_PASSWORD);
        }
    }
}
