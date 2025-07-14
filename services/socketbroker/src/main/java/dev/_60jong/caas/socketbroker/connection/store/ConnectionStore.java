package dev._60jong.caas.socketbroker.connection.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Socket Pool을 이용하기 위해 Queue(LinkedList)를 사용해
 * Socket 객체를 저장한다.
 */
@Slf4j
@Component
public class ConnectionStore {

    private final Map<String, LinkedList<Socket>> connections = new ConcurrentHashMap<>();

    /**
     * 가장 먼저 저장된 Socket 객체를 반환한다.
     *
     * @param id key
     * @return socket instance
     */
    public Socket pollConnection(final String id) {
        return connections.get(id).poll();
    }

    /**
     * ID에 Socket 객체를 저장한다.
     *
     * @param id key
     * @param socket target socket
     * @return total saved connections
     */
    public int saveConnection(final String id, final Socket socket) {
        connections.computeIfAbsent(id, k -> new LinkedList<>())
                .add(socket);

        log.debug("New connection({}) saved.", id);
        return connections.get(id).size();
    }
}
