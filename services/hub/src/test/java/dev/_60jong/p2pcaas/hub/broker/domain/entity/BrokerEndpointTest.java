package dev._60jong.p2pcaas.hub.broker.domain.entity;

import dev._60jong.p2pcaas.hub.broker.exception.InvalidBrokerEndpointSequenceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrokerEndpointTest {

    @DisplayName("존재하는 sequence를 통해 BrokerEndpoint를 가져온다.")
    @Test
    void test_get_BrokerEndpoint_by_seq() {
        // given
        int messiSeq = 0;
        BrokerEndpoint expected = BrokerEndpoint.MESSI;

        // when
        BrokerEndpoint actual = BrokerEndpoint.getBySequence(messiSeq);

        // then
        assertEquals(expected, actual);
    }

    @DisplayName("존재하지 않는는 sequence를 통해 BrokerEndpoint를 조회하면 예외가 발생한다.")
    @Test
    void test_get_BrokerEndpoint_by_invalid_seq() {
        // given
        int invalidSeq = -1;

        // then
        assertThrows(
                InvalidBrokerEndpointSequenceException.class,
                () -> BrokerEndpoint.getBySequence(invalidSeq)
        );
    }

}