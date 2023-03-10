package org.client.net;

import org.client.net.out.TempDecoder;

public class PacketConstants {

    public static final int TEMP_OPCODE = 1;

    public static final PacketDecoder[] PACKET_DECODERS = new PacketDecoder[1];

    static {
        PACKET_DECODERS[0] = new TempDecoder();
    }

    /**
     * Private constructor to avoid instantiation
     */
    private PacketConstants() {
    }

}
