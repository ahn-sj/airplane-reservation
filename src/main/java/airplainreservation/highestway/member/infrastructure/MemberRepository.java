package airplainreservation.highestway.member.infrastructure;

import airplainreservation.highestway.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

}
