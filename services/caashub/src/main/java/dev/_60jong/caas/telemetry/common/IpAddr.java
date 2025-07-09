package dev._60jong.caas.telemetry.common;

import lombok.Getter;

@Getter
public class IpAddr {
    private String ip;
    
    public IpAddr(String ip) {
        if (ip == null || ip.isEmpty()) {
            throw new NullPointerException("ip is null or empty");
        }

        if (!validIp(ip)) {
            throw new IllegalArgumentException(String.format("ip address %s is not valid", ip));
        }

        this.ip = ip;
    }
    
    private boolean validIp(final String ip) {
        String[] octetStrings = ip.split("\\.");

        if (octetStrings.length != 4) {
            return false;
        }

        for (String octetString : octetStrings) {
            try {
                int octet = Integer.parseInt(octetString);
            }  catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }
}
