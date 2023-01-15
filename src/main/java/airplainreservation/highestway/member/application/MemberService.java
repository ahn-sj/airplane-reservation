package airplainreservation.highestway.member.application;

import airplainreservation.highestway.common.dto.TokenResponse;
import airplainreservation.highestway.common.exception.CustomCommonException;
import airplainreservation.highestway.common.exception.ErrorCode;
import airplainreservation.highestway.member.domain.Member;
import airplainreservation.highestway.member.infrastructure.MemberRepository;
import airplainreservation.highestway.common.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

import static airplainreservation.highestway.member.dto.request.MemberRequest.MemberLoginRequest;
import static airplainreservation.highestway.member.dto.response.MemberResponse.MemberFindResponse;
import static airplainreservation.highestway.common.security.TokenProvider.ACCESS_TOKEN;
import static airplainreservation.highestway.common.security.TokenProvider.BEARER;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public Long registerMember(Member member) {
        checkUniqueEmail(member);
        checkUniqueUsername(member);

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

    public MemberFindResponse find(Long memberId) {
        Member member = findMember(memberId);
        return MemberFindResponse.of(member);
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new CustomCommonException(ErrorCode.NOT_EXISTS_MEMBER)
        );
    }

    private void checkUniqueUsername(Member member) {
        if(memberRepository.existsByUsername(member.getUsername())) {
            throw new CustomCommonException(ErrorCode.DUPLICATE_USERNAME);
        }
    }

    private void checkUniqueEmail(Member member) {
        if(memberRepository.existsByEmail(member.getEmail())) {
            throw new CustomCommonException(ErrorCode.DUPLICATE_EMAIL);
        }
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

    private void addTokenHeader(HttpServletResponse response, TokenResponse tokenResponse) {
        response.addHeader(ACCESS_TOKEN, BEARER + tokenResponse.getAccessToken());
//        response.addHeader(TokenProvider.REFRESH_TOKEN, tokenResponse.getRefreshToken());
    }
}
