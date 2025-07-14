package dev._60jong.caas.shared.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IpEncryptorTest {

    @DisplayName("암호화 결과는 빈 문자열이 아니다.")
    @Test
    void test_just_encrypt_not_empty() {
        // given
        String ip = "8.8.8.8";

        // when
        String encrypt = IpEncryptor.encrypt(ip);

        // then
        assertThat(encrypt).isNotEmpty();
    }

    @DisplayName("IP가 `SHA-256` 알고리즘으로 암호화된다.")
    @Test
    void test_encrypt_verification() {
        // given
        String ip = "8.8.8.8";
        String encryptedFromThirdParty = "838c4c2573848f58e74332341a7ca6bc5cd86a8aec7d644137d53b4d597f10f5";

        // when
        String encrypt = IpEncryptor.encrypt(ip);

        // then
        assertThat(encrypt).isEqualTo(encryptedFromThirdParty);
    }

    @DisplayName("IP와 암호화된 IP는 단방향으로 매칭된다.")
    @Test
    void test_ip_matches_encrypted() {
        // given
        String ip = "8.8.8.8";
        String encrypted = IpEncryptor.encrypt(ip);

        // then
        assertThat(IpEncryptor.match(ip, encrypted)).isTrue();
    }
}