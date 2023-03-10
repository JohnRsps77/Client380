package org.client.net;

import java.io.IOException;
import java.io.InputStream;

public interface PacketDecoder {
    void decode(InputStream inputStream) throws IOException;
}
