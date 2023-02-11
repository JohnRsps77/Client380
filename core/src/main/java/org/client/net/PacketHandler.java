package org.client.net;

import java.io.InputStream;

import static org.client.net.PacketConstants.PACKET_SIZES;

public class PacketHandler {

    private static final int CYCLES = 100;

    public static void handle(InputStream inputStream) {
        try {
            if(inputStream.available() == 0) {
                return;
            }

            int opcode = inputStream.read();
            int size = PACKET_SIZES[opcode];

            switch (opcode) {
                case PacketConstants.TEMP_OPCODE -> {

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
