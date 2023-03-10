package org.client.net;

import com.badlogic.gdx.Gdx;

import java.io.InputStream;

import static org.client.net.PacketConstants.PACKET_DECODERS;

public class PacketHandler {

    private static final int CYCLES = 100;

    public static void handleDecoder(InputStream inputStream) {
        try {
            if(inputStream.available() == 0) {
                return;
            }

            int opcode = inputStream.read();
            Gdx.app.log("Opcode", opcode + " of size " + inputStream.available());

            PACKET_DECODERS[opcode].decode(inputStream);

            int len = inputStream.available();
            if(len != 0) {
                Gdx.app.log("Opcode " + opcode, "Unhandled " + len + " bytes");
                // clear buffer
                inputStream.readNBytes(len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
