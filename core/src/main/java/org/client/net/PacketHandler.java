package org.client.net;

import static org.client.net.PacketConstants.PACKET_SIZES;

public class PacketHandler {

    public static void handle(int opcode) {

        int size = PACKET_SIZES[opcode];

        switch (opcode) {
            case PacketConstants.TEMP_OPCODE -> {

            }

        }
    }
}
