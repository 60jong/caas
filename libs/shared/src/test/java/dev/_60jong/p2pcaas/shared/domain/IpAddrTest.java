package dev._60jong.p2pcaas.shared.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IpAddrTest {

    @DisplayName("IpAddr 생성시 null을 넘기면 예외가 발생한다.")
    @Test
    void test_create_IpAddr_by_null_arg() {
        assertThrows(IllegalArgumentException.class, () -> new IpAddr(null));
    }

}