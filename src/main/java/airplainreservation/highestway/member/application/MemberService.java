package airplainreservation.highestway.member.application;

import airplainreservation.highestway.dto.MemberRequest;
import airplainreservation.highestway.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static airplainreservation.highestway.dto.MemberRequest.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void registerMember(MemberRegisterRequest memberRegisterRequest) {
//        if(memberRepository.existsByEmail(memberRegisterRequest.getEmail())) {
//            throw new CustomException(ErrorCode.);
//        }


    }
}
