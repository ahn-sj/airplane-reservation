package airplainreservation.highestway.member.application;

import airplainreservation.highestway.exception.CustomCommonException;
import airplainreservation.highestway.exception.ErrorCode;
import airplainreservation.highestway.member.domain.Member;
import airplainreservation.highestway.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

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
}
