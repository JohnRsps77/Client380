package org.client.net.out;

import com.badlogic.gdx.Gdx;
import org.client.net.PacketDecoder;

import java.io.IOException;
import java.io.InputStream;

public class TempDecoder implements PacketDecoder {

    @Override
    public void decode(InputStream inputStream) throws IOException {

        int received = inputStream.read();


        Gdx.app.log("PACKET", String.valueOf(received));
    }
}
