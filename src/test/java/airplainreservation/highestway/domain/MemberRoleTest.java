package airplainreservation.highestway.domain;

import airplainreservation.highestway.member.domain.MemberRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class MemberRoleTest {

    @DisplayName("입력한 사용자 권한의 유형이 나온다.")
    @ParameterizedTest
    @CsvSource({"ROLE_USER, 사용자", "ROLE_ADMIN, 관리자"})
    void memberRoleTest(MemberRole inputType, String expect) throws Exception {
        assertThat(inputType.getDesc()).isEqualTo(expect);
    }
}
